package ua.com.bzabza.ehcs.role.privilege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PrivilegeService {

    private final PrivilegeRepository privilegeRepository;

    @Autowired
    public PrivilegeService(PrivilegeRepository privilegeRepository) {
        this.privilegeRepository = privilegeRepository;
    }

    public void save(Privilege privilege) {
        privilegeRepository.persist(privilege);
    }

    public Privilege findOneByName(String name) {
        return privilegeRepository.findOneByName(name).orElseThrow(EntityNotFoundException::new);
    }
}
