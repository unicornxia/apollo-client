package fit.justin.apolloclient.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;
import java.util.Map;

public class CustomPropertySourceLocator implements ApplicationContextInitializer {

    @Value("${spring.servlet.port}")
    private Integer port;

    /**
     * Initialize the given application context.
     *
     * @param applicationContext the application to configure
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Map<String, Object> map = new HashMap<>();
        map.put("justin","2023");
        MapPropertySource mapPropertySource = new MapPropertySource("resilience4j-env", map);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        environment.getPropertySources().addFirst(mapPropertySource);
    }
}
