package fit.justin.apolloclient.design;

import fit.justin.apolloclient.callback.ZookeeperCallback;
import fit.justin.apolloclient.config.Config;
import fit.justin.apolloclient.watch.NodeWatch;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

public abstract class ConnectionState {

    @Resource
    protected ZooKeeper zooKeeper;

    @Resource
    protected Config config;

    @Resource
    protected ZookeeperCallback callback;

    @Value("${spring.application.name}")
    protected String APP_NAME;

    protected String getPath(String appName){
        return String.format("/%s",appName);
    }


    /**
     * 未知操作
     *
     * @param keeperState
     */
    public abstract void none(Watcher.Event.KeeperState keeperState);

    /**
     * 创建节点
     *
     * @param keeperState
     */
    public abstract void nodeCreated(Watcher.Event.KeeperState keeperState);

    /**
     * 删除节点
     *
     * @param keeperState
     */
    public abstract void nodeDeleted(Watcher.Event.KeeperState keeperState);

    /**
     * 节点数据变更
     *
     * @param keeperState
     */
    public abstract void nodeDataChanged(Watcher.Event.KeeperState keeperState);

    /**
     * 下级节点变更
     *
     * @param keeperState
     */
    public abstract void nodeChildrenChanged(Watcher.Event.KeeperState keeperState);

}
