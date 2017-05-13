package ua.com.bzabza.ehcs.microblog;

import lombok.Setter;
import ua.com.bzabza.ehcs.user.User;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Setter
public class MicroblogForm {

    @NotNull
    private String text;

    private User user;

    public Microblog toMicroblog() {
        Microblog microblog = new Microblog();
        microblog.setText(text);
        microblog.setCreationTime(new Date());
//        microblog.setUser(user);
        return microblog;
    }
}
