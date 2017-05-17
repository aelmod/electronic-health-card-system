package ua.com.bzabza.ehcs.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.bzabza.ehcs.role.Role;
import ua.com.bzabza.ehcs.role.RoleService;
import ua.com.bzabza.ehcs.role.privilege.Privilege;
import ua.com.bzabza.ehcs.role.privilege.PrivilegeService;
import ua.com.bzabza.ehcs.user.User;
import ua.com.bzabza.ehcs.user.UserService;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class FirstSeed {

    private final UserService userService;

    private final RoleService roleService;

    private final PrivilegeService privilegeService;

    @Autowired
    public FirstSeed(UserService userService, RoleService roleService, PrivilegeService privilegeService) {
        this.userService = userService;
        this.roleService = roleService;
        this.privilegeService = privilegeService;
    }

    @PostConstruct
    public void initData() {
        initRoles();
        createInitUser();
    }

    private void initRoles() {
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_DOCTOR", adminPrivileges);
        createRoleIfNotFound("ROLE_PATIENT", Collections.singletonList(readPrivilege));
    }

    private Privilege createPrivilegeIfNotFound(String name) {
        try {
            return privilegeService.findOneByName(name);
        } catch (EntityNotFoundException e) {
            Privilege privilege = new Privilege(name);
            privilegeService.save(privilege);
            return privilege;
        }
    }

    private Role createRoleIfNotFound(
            String name, Collection<Privilege> privileges) {
        try {
            return roleService.findOneByName(name);
        } catch (EntityNotFoundException e) {
            Role role = new Role(name);
            role.setPrivileges(privileges);
            roleService.save(role);
            return role;
        }
    }

    private void createInitUser() {
        User user = new User();
        user.setFullName("hyisos11Loh");
        user.setEmail("hyisos11@mail.ru");
        user.setUsername("hyisos11");
        user.setPassword("hyisos11");
        user.setSecret("IQTRIHV5V2UCFWNA");

        user.setRoles(Collections.singleton(roleService.findOneByName("ROLE_DOCTOR")));

        try {
            userService.getUserByUsername("hyisos11");
        } catch (EntityNotFoundException e) {
            userService.save(user);
        }
    }
}
