package fit.justin.apolloclient.listener;

import fit.justin.apolloclient.model.User;
import org.springframework.context.ApplicationEvent;

public class UserEvent extends ApplicationEvent {

    private User user;

    public UserEvent(Object source) {
        super(source);
        this.user = (User) source;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
