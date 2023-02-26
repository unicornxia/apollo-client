package fit.justin.apolloclient.design.impl;

import fit.justin.apolloclient.design.ConnectionStateConfig;
import fit.justin.apolloclient.design.StateHandle;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Service;

@Service
public class StateHandleImpl extends ConnectionStateConfig implements StateHandle {

    @Override
    public void none(Watcher.Event.KeeperState keeperState) {
        map.get(keeperState).none(keeperState);
    }

    @Override
    public void nodeCreated(Watcher.Event.KeeperState keeperState) {
        map.get(keeperState).nodeCreated(keeperState);
    }

    @Override
    public void nodeDeleted(Watcher.Event.KeeperState keeperState) {
        map.get(keeperState).nodeDeleted(keeperState);
    }

    @Override
    public void nodeDataChanged(Watcher.Event.KeeperState keeperState) {
        map.get(keeperState).nodeDataChanged(keeperState);
    }

    @Override
    public void nodeChildrenChanged(Watcher.Event.KeeperState keeperState) {
        map.get(keeperState).nodeChildrenChanged(keeperState);
    }
}
