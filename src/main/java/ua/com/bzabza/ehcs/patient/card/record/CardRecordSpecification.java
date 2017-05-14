package ua.com.bzabza.ehcs.patient.card.record;

import ua.com.bzabza.ehcs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CardRecordSpecification implements EntitySpecification<CardRecord> {

    @Override
    public CriteriaQuery<CardRecord> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<CardRecord> microblogQuery = cb.createQuery(CardRecord.class);
        Root<CardRecord> from = microblogQuery.from(CardRecord.class);
        return microblogQuery.select(from);
    }
}
