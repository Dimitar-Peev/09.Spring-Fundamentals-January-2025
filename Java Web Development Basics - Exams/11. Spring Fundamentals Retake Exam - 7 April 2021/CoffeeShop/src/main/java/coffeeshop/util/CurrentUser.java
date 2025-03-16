package coffeeshop.util;

import coffeeshop.model.service.UserServiceModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@SessionScope
public class CurrentUser {

    private String id;
    private String username;

    public void login(UserServiceModel user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public void logout() {
        this.id = null;
        this.username = null;
    }

    public boolean isLogged() {
        return this.getId() != null;
    }
}
