package ua.com.bzabza.ehcs.card;

import lombok.Setter;
import ua.com.bzabza.ehcs.patient.Patient;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Setter
@Entity
public class Card {

    @OneToOne
    private Patient patient;

    private String patienInfo;
}
