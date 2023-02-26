package fit.justin.apolloclient.config;

import java.util.Map;

public abstract class Config {

    public abstract void put(String path, String data);

    public abstract void remove(String path);

    public abstract void removeAll();


    public abstract Map<String, Object> get(String path);

    public abstract String getAll();


}
