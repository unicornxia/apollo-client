package fit.justin.apolloclient.design;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import fit.justin.apolloclient.design.ConnectionState;
import fit.justin.apolloclient.design.state.*;
import org.apache.zookeeper.Watcher.Event.KeeperState;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionStateConfig {
    @Resource
    private AuthFailedState authFailedState;

    @Resource
    private DisconnectedState disconnectedState;

    @Resource
    private ExpiredState expiredState;

    @Resource
    private ReadOnlyState readOnlyState;

    @Resource
    private SaslAuthenticatedState saslAuthenticatedState;

    @Resource
    private SyncConnectedState syncConnectedState;

    protected final Map<KeeperState, ConnectionState> map = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        map.put(KeeperState.AuthFailed, authFailedState);
        map.put(KeeperState.Disconnected, disconnectedState);
        map.put(KeeperState.Expired, expiredState);
        map.put(KeeperState.ConnectedReadOnly, readOnlyState);
        map.put(KeeperState.SaslAuthenticated, saslAuthenticatedState);
        map.put(KeeperState.SyncConnected, syncConnectedState);
    }
}
