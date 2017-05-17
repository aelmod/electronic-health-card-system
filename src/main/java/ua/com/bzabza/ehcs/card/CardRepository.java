package ua.com.bzabza.ehcs.card;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;

@Repository
public class CardRepository extends BaseRepository<Card, Integer> {

    public CardRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
