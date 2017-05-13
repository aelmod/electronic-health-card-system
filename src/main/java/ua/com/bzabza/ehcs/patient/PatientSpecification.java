package ua.com.bzabza.ehcs.patient;

import ua.com.bzabza.ehcs.EntitySpecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PatientSpecification implements EntitySpecification<Patient> {

    private Optional<String> oFullName = Optional.empty();

    private Optional<Date> oDate = Optional.empty();

    @Override
    public CriteriaQuery<Patient> toCriteria(CriteriaBuilder cb) {
        CriteriaQuery<Patient> patientQuery = cb.createQuery(Patient.class);
        Root<Patient> patientEntity = patientQuery.from(Patient.class);
        patientQuery.select(patientEntity);

        List<Predicate> predicateList = new ArrayList<>();
        oFullName.ifPresent(fullName -> predicateList.add(cb.equal(patientEntity.get("full_name"), fullName)));
        oDate.ifPresent(date -> predicateList.add(cb.equal(patientEntity.get("date"), date)));
        Predicate mainPredicate = cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        patientQuery.where(mainPredicate);
        return patientQuery;
    }
}
