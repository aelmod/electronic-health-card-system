package ua.com.bzabza.ehcs.user;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import ua.com.bzabza.ehcs.role.Role;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Objects;

@Getter
@Setter
public class UserRegisterForm {

    @NotNull
    @NotEmpty
    private String fullName;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 12)
    private String login;

    @NotNull
    private Integer roleId;

    @NotNull
    @NotEmpty
    @Size(min = 8, max = 32)
    private String password;

    private transient String confirmPassword;

    @AssertTrue(message = "The passwords you provided do not match. Please correct and resubmit.")
    private boolean isValidConfirmedPassword() {
        return Objects.equals(password, confirmPassword);
    }

    public User toUser() {
        User user = new User(fullName, email, login, password);
        Role role = new Role();
        role.setId(roleId);
        user.setRoles(Collections.singletonList(role));
        return user;
    }
}
