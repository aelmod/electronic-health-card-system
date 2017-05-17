package ua.com.bzabza.ehcs.card.record;

import ua.com.bzabza.ehcs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class RecordSpecification implements EntitySpecification<Record> {

    @Override
    public CriteriaQuery<Record> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Record> microblogQuery = cb.createQuery(Record.class);
        Root<Record> from = microblogQuery.from(Record.class);
        return microblogQuery.select(from);
    }
}
