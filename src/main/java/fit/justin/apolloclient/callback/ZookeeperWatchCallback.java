package fit.justin.apolloclient.callback;

import com.alibaba.fastjson.JSON;
import fit.justin.apolloclient.config.Config;
import fit.justin.apolloclient.design.StateHandle;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class ZookeeperWatchCallback implements Watcher, AsyncCallback.StatCallback, AsyncCallback.DataCallback, AsyncCallback.ChildrenCallback {

    @Resource
    private StateHandle stateHandle;

    @Resource
    private Config config;

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("WatchCallBack:{}", JSON.toJSONString(watchedEvent));

        switch (watchedEvent.getType()) {
            case None:
                stateHandle.none(watchedEvent.getState());
                break;
            case NodeCreated:
                // getData 会触发 watch 和 DataCallback
                // 创建节点，通过DataCallback刚创建的配置同步到本地
                stateHandle.nodeCreated(watchedEvent.getState());
                System.out.println("========== 创建my conf节点 ==========");
                //zooKeeper.getData("/" + APP_NAME, this, new ZookeeperCallback(zooKeeper, this, config), "test");
                break;
            case NodeDeleted:
                // 容错性
                System.out.println("========== 删除my conf节点 ==========");
                //config.remove(watchedEvent.getPath());
                break;
            case NodeDataChanged:
                // 修改节点，通过DataCallback刚创建的配置同步到本地
                System.out.println("========== 修改my conf节点 ==========");
                //zooKeeper.getData("/" + APP_NAME, this, new ZookeeperCallback(zooKeeper, this, config), "test");
                break;
            case NodeChildrenChanged:
                System.out.println("========== 下级节点变更 ==========");
                //zooKeeper.getData("/" + APP_NAME, this, new ZookeeperCallback(zooKeeper, this, config), "test");
                break;
            /*case DataWatchRemoved:
                break;
            case ChildWatchRemoved:
                break;
            case PersistentWatchRemoved:
                break;*/
        }
    }

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
        /*if (stat != null) {
            zooKeeper.getData("/myconf", this, "test");
        } else {
            log.warn("》》》》 this node :{} is not exist 《《《《",path);
        }*/
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
        if (data != null) {
            String dataStr = new String(data);
            config.put(path, dataStr);
            //countDownLatch.countDown();
        }
    }

    @Override
    public void processResult(int rc, String path, Object ctx, List<String> children) {
        log.info("下级节点回调 rc:{},path:{},ctx:{},children:{}", rc, path, ctx, children);
    }
}
