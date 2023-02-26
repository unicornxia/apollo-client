package fit.justin.apolloclient.callback;

import fit.justin.apolloclient.config.Config;
import fit.justin.apolloclient.watch.NodeWatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Component
public class ZookeeperCallback implements AsyncCallback.StatCallback, AsyncCallback.DataCallback, AsyncCallback.ChildrenCallback {

    @Resource
    private ZooKeeper zooKeeper;

    @Resource
    private Config config;

    @Resource
    private NodeWatch nodeWatch;

    @Resource
    private ConfigurableApplicationContext configurableApplicationContext;


    /**
     * 状态回调
     *
     * @param rc   – 调用的返回代码或结果
     * @param path – 我们传递给异步调用的路径
     * @param ctx  – 我们传递给异步调用的任何上下文对象
     * @param stat – Stat 给定路径上节点的对象。
     */
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        log.info("状态回调 rc:{},path:{},ctx:{},stat:{}", rc, path, ctx, stat);
    }


    /**
     * 数据回调
     *
     * @param rc   – 调用的返回代码或结果
     * @param path – 我们传递给异步调用的路径
     * @param ctx  – 我们传递给异步调用的任何上下文对象
     * @param data org.apache.zookeeper.server.DataNode.data– 节点的
     * @param stat – Stat 给定路径上节点的对象
     */
    @Override
    public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
        log.info("数据回调 rc:{},path:{},ctx:{},stat:{},data:{}", rc, path, ctx, stat, data);
        if (Objects.nonNull(data)) {
            config.put(path, new String(data));

            Map<String, Object> map = new HashMap<>();
            map.put("justin",new String(data));
            log.info("data:{}",new String(data));
            MapPropertySource mapPropertySource = new MapPropertySource("resilience4j-env", map);
            ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
            environment.getPropertySources().addFirst(mapPropertySource);


            //propertySourcesPlaceholderConfigurer.setEnvironment();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> childrenList) {
        //log.info("下级节点回调 rc:{},path:{},ctx:{},children:{}", rc, path, ctx, childrenList);
        if (CollectionUtils.isEmpty(childrenList)) {
            config.removeAll();
            return;
        }
        childrenList.forEach(children -> zooKeeper.getData(path + "/" + children, nodeWatch, this, null));
    }
}
