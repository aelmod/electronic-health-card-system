package ua.com.bzabza.ehcs.card.record.commentary;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;

@Repository
public class CommentaryRepository extends BaseRepository<Commentary, Integer> {

    public CommentaryRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
