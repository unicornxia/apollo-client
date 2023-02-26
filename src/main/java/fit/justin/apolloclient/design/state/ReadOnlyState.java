package fit.justin.apolloclient.design.state;

import fit.justin.apolloclient.design.ConnectionState;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

/**
 * 只读
 */
@Slf4j
@Component
public class ReadOnlyState extends ConnectionState {

    /**
     * 未知操作
     *
     * @param keeperState
     */
    @Override
    public void none(Watcher.Event.KeeperState keeperState) {
        log.info("zookeeper只读，未知操作...");
    }

    /**
     * 创建节点
     *
     * @param keeperState
     */
    @Override
    public void nodeCreated(Watcher.Event.KeeperState keeperState) {
        log.info("zookeeper只读，创建节点...");
    }

    /**
     * 删除节点
     *
     * @param keeperState
     */
    @Override
    public void nodeDeleted(Watcher.Event.KeeperState keeperState) {
        log.info("zookeeper只读，删除节点...");
    }

    /**
     * 节点数据变更
     *
     * @param keeperState
     */
    @Override
    public void nodeDataChanged(Watcher.Event.KeeperState keeperState) {
        log.info("zookeeper会话只读，节点数据变更...");
    }

    /**
     * 下级节点变更
     *
     * @param keeperState
     */
    @Override
    public void nodeChildrenChanged(Watcher.Event.KeeperState keeperState) {
        log.info("zookeeper只读，下级节点变更...");
    }
}
