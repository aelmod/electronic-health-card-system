package ua.com.bzabza.ehcs.country;

import ua.com.bzabza.ehcs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CountrySpecification implements EntitySpecification<Country> {

    @Override
    public CriteriaQuery<Country> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Country> countryQuery = cb.createQuery(Country.class);
        Root<Country> from = countryQuery.from(Country.class);
        return countryQuery.select(from);
    }
}
