package fit.justin.apolloclient.service;


import fit.justin.apolloclient.callback.ZookeeperCallback;
import fit.justin.apolloclient.config.Config;
import fit.justin.apolloclient.watch.NodeWatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TaskService {

    public static final long TIME = 1500;

    @Value("${spring.application.name}")
    public String APP_NAME;

    private final ScheduledExecutorService scheduledExecutorService;

    private final ZooKeeper zooKeeper;

    private final ZookeeperCallback callback;

    private final NodeWatch nodeWatch;

    @Resource
    private Config config;

    public TaskService(ZooKeeper zooKeeper, ZookeeperCallback callback, NodeWatch nodeWatch) {
        scheduledExecutorService = new ScheduledThreadPoolExecutor(1, new BasicThreadFactory.Builder().namingPattern("zk-schedule-pool-%d").daemon(true).build());
        this.zooKeeper = zooKeeper;
        this.callback = callback;
        this.nodeWatch = nodeWatch;
    }

    @PostConstruct
    public void initialize() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            try {
                zooKeeper.getChildren("/" + APP_NAME, nodeWatch, callback, "justin");
                log.info(config.getAll());
            } catch (Throwable ex) {
                log.error(ex.getMessage(), ex);
            }
        }, TIME, TIME, TimeUnit.MILLISECONDS);

    }


}
