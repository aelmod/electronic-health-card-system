package ua.com.bzabza.ehcs.patient;

import org.springframework.stereotype.Repository;
import ua.com.bzabza.ehcs.BaseRepository;

import javax.persistence.EntityManager;

@Repository
public class PatientRepository extends BaseRepository<Patient, Integer> {

    public PatientRepository(EntityManager entityManager) {
        super(entityManager);
    }
}
