package ua.com.bzabza.ehcs.user.specialization;

import ua.com.bzabza.ehcs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class SpecializationSpecification implements EntitySpecification<Specialization> {
    @Override
    public CriteriaQuery<Specialization> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Specialization> specializationQuery = cb.createQuery(Specialization.class);
        Root<Specialization> specializationEntity = specializationQuery.from(Specialization.class);
        return specializationQuery.select(specializationEntity);
    }
}
