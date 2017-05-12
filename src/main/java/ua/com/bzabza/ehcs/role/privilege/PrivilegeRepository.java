package ua.com.bzabza.ehcs.role.privilege;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class PrivilegeRepository extends BaseRepository<Privilege, Integer> {

    public PrivilegeRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Privilege> findOneByName(String name) {
        PrivilegeSpecification privilegeSpecification = new PrivilegeSpecification();
        privilegeSpecification.setOName(Optional.of(name));
        List<Privilege> privilege = findBy(privilegeSpecification);
        if (privilege.size() > 1) throw new IllegalStateException("privilege name is unique property");
        if (privilege.isEmpty()) return Optional.empty();
        return Optional.of(privilege.get(0));
    }
}
