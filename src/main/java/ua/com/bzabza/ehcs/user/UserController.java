package ua.com.bzabza.ehcs.user;

import com.fasterxml.jackson.annotation.JsonView;
import org.jboss.aerogear.security.otp.api.Base32;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.bzabza.ehcs.patient.Patient;
import ua.com.bzabza.ehcs.role.RoleService;
import ua.com.bzabza.ehcs.security.CurrentUser;
import ua.com.bzabza.ehcs.security.google2fa.User2faToken;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    @JsonView(User.MinimalView.class)
    public List<User> getAll(UserSpecification userSpecification) {
        return userService.findBy(userSpecification);
    }

    @GetMapping("{userId}")
    @JsonView(User.FullView.class)
    public User getById(@PathVariable int userId) {
        return userService.getByPk(userId);
    }

    @GetMapping("currentUser")
    @JsonView(User.MinimalView.class)
    public User getCurrentUser(@CurrentUser User user) {
        return user;
    }

    @GetMapping("patients")
    @JsonView(Patient.AllPrimitivesView.class)
    public List<Patient> getPatients(@CurrentUser User user) {
        return user.getPatients();
    }

    @PostMapping("register")
    public User2faToken registerUser(@RequestBody @Valid UserRegisterForm registerForm) {
        User registeredUser = registerForm.toUser();
        registeredUser.setSecret(Base32.random());
//        registeredUser.setRoles(Collections.singletonList(roleService.findOneByName("ROLE_USER")));
        return userService.save(registeredUser);
    }
}
