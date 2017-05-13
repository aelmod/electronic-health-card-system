package ua.com.bzabza.ehcs.microblog;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;

@Repository
public class MicroblogRepository extends BaseRepository<Microblog, Integer> {

    public MicroblogRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
