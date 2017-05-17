package ua.com.bzabza.ehcs.card.record;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;

@Repository
public class RecordRepository extends BaseRepository<Record, Integer> {

    public RecordRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
