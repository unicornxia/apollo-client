package fit.justin.apolloclient.config;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author justin_xiao
 */
@Component
public class PropertiesConfig extends Config {


    @Resource
    private ConfigurableEnvironment configurableEnvironment;


    private final Map<String, Map<String, Object>> mapMap = new ConcurrentHashMap<>();

    @Override
    public void put(String path, String data) {
        mapMap.put(path, getData(data));
        //configurableEnvironment.getPropertySources().replace("resilience4j-env",new MapPropertySource("resilience4j-env", mapMap.get(path)));
    }

    @Override
    public void remove(String path) {
        mapMap.remove(path);
    }

    @Override
    public void removeAll() {
        mapMap.clear();
    }

    @Override
    public Map<String, Object> get(String path) {
        return mapMap.get(path);
    }

    @Override
    public String getAll() {
        return JSON.toJSONString(mapMap);
    }

    public Map<String, Object> getData(String data) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.hasText(data)) {
            String[] split = data.split("=");
            map.put(split[0], split[1]);
        }
        return map;
    }

    @Data
    public static class DataContext {

        private String path;

        private String key;

        private String value;
    }
}
