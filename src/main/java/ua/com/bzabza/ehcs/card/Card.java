package ua.com.bzabza.ehcs.card;

import lombok.Getter;
import lombok.Setter;
import ua.com.bzabza.ehcs.patient.Patient;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Patient patient;

    private String patienInfo;
}
