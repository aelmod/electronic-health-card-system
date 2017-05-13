package ua.com.bzabza.ehcs.city;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;

@Repository
public class CityRepository extends BaseRepository<City, Integer> {

    public CityRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
