package ua.com.bzabza.ehcs.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.bzabza.ehcs.security.google2fa.TokenGenerator;
import ua.com.bzabza.ehcs.security.google2fa.User2faToken;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true)
    public List<Patient> getBy(PatientSpecification patientSpecification) {
        return patientRepository.findBy(patientSpecification);
    }

    @Transactional
    public User2faToken save(Patient patient) {
        String encodedPassword = new BCryptPasswordEncoder().encode(patient.getPassword());
        patient.setPassword(encodedPassword);
        patientRepository.persist(patient);
        return new TokenGenerator<Patient>().generateQRUrl(patient);
    }
}
