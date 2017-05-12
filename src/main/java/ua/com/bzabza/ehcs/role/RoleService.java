package ua.com.bzabza.ehcs.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void save(Role role) {
        roleRepository.persist(role);
    }

    public Role findOneByName(String name) {
        return roleRepository.findOneByName(name).orElseThrow(EntityNotFoundException::new);
    }
}
