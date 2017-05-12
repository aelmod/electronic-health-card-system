package ua.com.bzabza.ehcs.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bzabza.ehcs.user.User;
import ua.com.bzabza.ehcs.user.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

@Component
public class FirstSeed {

    private final UserService userService;

    @Autowired
    public FirstSeed(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {
        User user = new User("test", "test@test.com", "test", "pass");
        user.setSecret("D32K7NDRILUMMXP6");
        try {
            userService.getUserByUsername("test");
        } catch (EntityNotFoundException e) {
            userService.save(user);
        }
    }
}
