package ua.com.bzabza.ehcs.role;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepository extends BaseRepository<Role, Integer> {


    public RoleRepository(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Role> findOneByName(String name) {
        RoleSpecification roleSpecification = new RoleSpecification();
        roleSpecification.setOName(Optional.of(name));
        List<Role> role = findBy(roleSpecification);
        if (role.size() > 1) throw new IllegalStateException("role name is unique property");
        if (role.isEmpty()) return Optional.empty();
        return Optional.of(role.get(0));
    }
}
