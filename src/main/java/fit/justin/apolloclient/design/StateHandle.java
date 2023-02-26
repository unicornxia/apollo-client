package fit.justin.apolloclient.design;

import org.apache.zookeeper.Watcher.Event.KeeperState;

public interface StateHandle {

    /**
     * 未知状态
     * @param keeperState
     */
    void none(KeeperState keeperState);

    /**
     * 创建节点
     * @param keeperState
     */
    void nodeCreated(KeeperState keeperState);

    /**
     * 删除节点
     * @param keeperState
     */
    void nodeDeleted(KeeperState keeperState);

    /**
     * 节点数据变更
     * @param keeperState
     */
    void nodeDataChanged(KeeperState keeperState);

    /**
     * 下级节点变更
     * @param keeperState
     */
    void nodeChildrenChanged(KeeperState keeperState);
}
