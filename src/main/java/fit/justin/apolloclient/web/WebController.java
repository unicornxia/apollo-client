package fit.justin.apolloclient.web;


import fit.justin.apolloclient.listener.UserEvent;
import fit.justin.apolloclient.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {


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
}
