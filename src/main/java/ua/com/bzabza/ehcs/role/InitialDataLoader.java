package ua.com.bzabza.ehcs.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.bzabza.ehcs.role.privilege.Privilege;
import ua.com.bzabza.ehcs.role.privilege.PrivilegeService;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class InitialDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final RoleService roleService;

    private final PrivilegeService privilegeService;

    @Autowired
    public InitialDataLoader(RoleService roleService,
                             PrivilegeService privilegeService) {
        this.roleService = roleService;
        this.privilegeService = privilegeService;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (alreadySetup) return;
        Privilege readPrivilege
                = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege
                = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        List<Privilege> adminPrivileges = Arrays.asList(
                readPrivilege, writePrivilege);
        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", Collections.singletonList(readPrivilege));
        alreadySetup = true;
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
        try {
            return privilegeService.findOneByName(name);
        } catch (EntityNotFoundException e) {
            Privilege privilege = new Privilege(name);
            privilegeService.save(privilege);
            return privilege;
        }
    }

    @Transactional
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
}