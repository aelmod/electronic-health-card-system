package ua.com.bzabza.ehcs.patient.card.record;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;

@Repository
public class CardRecordRepository extends BaseRepository<CardRecord, Integer> {

    public CardRecordRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
