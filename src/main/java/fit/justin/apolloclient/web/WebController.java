package fit.justin.apolloclient.web;


import fit.justin.apolloclient.listener.UserEvent;
import fit.justin.apolloclient.model.User;
import fit.justin.apolloclient.service.ZookeeperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author justin_xiao
 */
@Slf4j
@RestController
public class WebController {

    @Resource
    private ZookeeperService zookeeperService;

    private final ApplicationEventPublisher publisher;

    public WebController(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }


    @RequestMapping("test")
    public String test(@Value("${justin:justin}") String name) {
        User user = new User();
        user.setName(name);
        publisher.publishEvent(new UserEvent(user));
        return name;
    }

    @RequestMapping("create")
    public String create(String path, String data) throws InterruptedException, KeeperException {
        return zookeeperService.create(path, data);
    }

    @RequestMapping("getNodeData")
    public String getNodeData(String path) throws InterruptedException, KeeperException {
        return new String(zookeeperService.getNodeData(path));
    }

    @RequestMapping("updateNodeData")
    public String updateNodeData(String path, String data) throws InterruptedException, KeeperException {
        return zookeeperService.updateNodeData(path, data) + "";
    }

    @RequestMapping("delete")
    public String delete(String path) {
        return zookeeperService.deleteNode(path) + "";
    }

}
