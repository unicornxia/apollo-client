package fit.justin.apolloclient.watch;

import com.alibaba.fastjson.JSON;
import fit.justin.apolloclient.design.StateHandle;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class NodeWatch implements Watcher {

/*    @Resource
    private StateHandle stateHandle;*/

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("WatchCallBack:{}", JSON.toJSONString(watchedEvent));

        switch (watchedEvent.getType()) {
            case None:
                //stateHandle.none(watchedEvent.getState());
                log.info("未知事件:{}",watchedEvent.getPath());
                break;
            case NodeCreated:
                // getData 会触发 watch 和 DataCallback
                // 创建节点，通过DataCallback刚创建的配置同步到本地
                //stateHandle.nodeCreated(watchedEvent.getState());
                log.info("创建节点:{}",watchedEvent.getPath());
                //zooKeeper.getData("/" + APP_NAME, this, new ZookeeperCallback(zooKeeper, this, config), "test");
                break;
            case NodeDeleted:
                // 容错性
                //stateHandle.nodeDeleted(watchedEvent.getState());
                log.info("删除节点:{}",watchedEvent.getPath());
                //config.remove(watchedEvent.getPath());
                break;
            case NodeDataChanged:
                //stateHandle.nodeDataChanged(watchedEvent.getState());
                // 修改节点，通过DataCallback刚创建的配置同步到本地
                log.info("变更节点数据:{}",watchedEvent.getPath());
                //zooKeeper.getData("/" + APP_NAME, this, new ZookeeperCallback(zooKeeper, this, config), "test");
                break;
            case NodeChildrenChanged:
                //stateHandle.nodeChildrenChanged(watchedEvent.getState());
                log.info("下级节点变更:{}",watchedEvent.getPath());
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


}
