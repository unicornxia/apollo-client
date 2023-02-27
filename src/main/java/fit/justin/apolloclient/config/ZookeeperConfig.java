package fit.justin.apolloclient.config;

import fit.justin.apolloclient.watch.ConnectionWatch;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class ZookeeperConfig {


    @Value("${zookeeper.address}")
    private String ADDRESS;

    @Value("${zookeeper.timeout}")
    private Integer TIME_OUT;


    @Bean
    public ZooKeeper getZookeeper() throws Exception {
        log.info("init zookeeper...");
        log.info("addressList:{}", TIME_OUT);
        return new ZooKeeper(ADDRESS, TIME_OUT, new ConnectionWatch());
    }
}

