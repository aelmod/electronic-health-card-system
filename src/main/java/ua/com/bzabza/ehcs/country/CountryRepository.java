package ua.com.bzabza.ehcs.country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;

@Repository
public class CountryRepository extends BaseRepository<Country, Integer> {

    @Autowired
    public CountryRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
