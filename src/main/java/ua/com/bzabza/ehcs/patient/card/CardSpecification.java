package ua.com.bzabza.ehcs.patient.card;

import ua.com.bzabza.ehcs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CardSpecification implements EntitySpecification<Card> {
    @Override
    public CriteriaQuery<Card> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Card> cardQuery = cb.createQuery(Card.class);
        Root<Card> cardEntity = cardQuery.from(Card.class);
        return cardQuery.select(cardEntity);
    }
}
