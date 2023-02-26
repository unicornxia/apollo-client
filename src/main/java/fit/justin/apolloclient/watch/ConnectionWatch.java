package fit.justin.apolloclient.watch;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

@Slf4j
public class ConnectionWatch implements Watcher {

    // 通过watch去监听事件，发现状态为 SyncConnected ，说明已连接，释放等待
    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("ConnectionWatch:{}",JSON.toJSONString(watchedEvent));
        switch (watchedEvent.getState()) {
            case Unknown:
                break;
            case Disconnected:
                break;
            case NoSyncConnected:
                break;
            case SyncConnected:
                System.out.println("》》》》》》 zookeeper connection .... 《《《《《《");
                break;
            case AuthFailed:
                break;
            case ConnectedReadOnly:
                break;
            case SaslAuthenticated:
                break;
            case Expired:
                break;
            /*case Closed:
                break;*/
        }
    }
}
