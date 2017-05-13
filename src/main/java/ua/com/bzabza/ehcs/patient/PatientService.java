package ua.com.bzabza.ehcs.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.bzabza.ehcs.patient.card.Card;
import ua.com.bzabza.ehcs.patient.card.CardService;
import ua.com.bzabza.ehcs.security.google2fa.TokenGenerator;
import ua.com.bzabza.ehcs.security.google2fa.User2faToken;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    private final CardService cardService;

    @Autowired
    public PatientService(PatientRepository patientRepository, CardService cardService) {
        this.patientRepository = patientRepository;
        this.cardService = cardService;
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
        saveCard(patient);
        return new TokenGenerator<Patient>().generateQRUrl(patient);
    }

    @Transactional
    public Patient getByPk(Integer id) {
        return patientRepository.findOneByPk(id).orElseThrow(EntityNotFoundException::new);
    }

    private void saveCard(Patient patient) {
        Card card = new Card();
        card.setPatient(patient);
        cardService.save(card);
    }
}
