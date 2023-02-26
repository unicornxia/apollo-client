package fit.justin.apolloclient.config;

import fit.justin.apolloclient.watch.ConnectionWatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class ZookeeperConfig {
    private final static String ADDR = "175.178.241.47:2181";
    private final static Integer TIME_OUT = 5000;

    @Bean
    public ZooKeeper getZookeeper() throws Exception {
        log.info("init zookeeper...");
        return new ZooKeeper(ADDR, TIME_OUT,new ConnectionWatch());
    }
}

