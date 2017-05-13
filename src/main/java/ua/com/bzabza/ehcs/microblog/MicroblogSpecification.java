package ua.com.bzabza.ehcs.microblog;

import ua.com.bzabza.ehcs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class MicroblogSpecification implements EntitySpecification<Microblog> {

    @Override
    public CriteriaQuery<Microblog> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Microblog> microblogQuery = cb.createQuery(Microblog.class);
        Root<Microblog> from = microblogQuery.from(Microblog.class);
        return microblogQuery.select(from);
    }
}
