package fit.justin.apolloclient.service;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

public class ZookeeperService {

    @Resource
    private ZooKeeper zooKeeper;

    public String create(String path, String data) throws InterruptedException, KeeperException {
        return zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    }


    public byte[] getNodeData(String path) throws InterruptedException, KeeperException {
        return zooKeeper.getData(path, false, null);
    }

    public boolean updateNodeData(String path, String data) throws InterruptedException, KeeperException {
        zooKeeper.setData(path, data.getBytes(), -1);
        String s = new String(getNodeData(path));
        if (Objects.equals(data, s)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean nodeExists(String path) throws InterruptedException, KeeperException {
        return Objects.nonNull(zooKeeper.exists(path, false));
    }

    public List<String> getChildrenNodes(String path) throws InterruptedException, KeeperException {
        return zooKeeper.getChildren(path, false);
    }


    public boolean deleteNode(String path)  {
        try {
            zooKeeper.delete(path, -1);
        } catch (InterruptedException | KeeperException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


}
