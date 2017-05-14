package ua.com.bzabza.ehcs.patient.card;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ua.com.bzabza.ehcs.patient.card.record.CardRecord;
import ua.com.bzabza.ehcs.patient.Patient;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "patient_card",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    @JsonView(FullView.class)
    private Patient patient;

    @OneToMany(mappedBy = "card")
    @JsonView(FullView.class)
    private List<CardRecord> cardRecords = new ArrayList<>();

    private String patientInfo;

    public interface MinimalView {}

    public interface FullView extends MinimalView, Patient.FullView, CardRecord.MinimalView {}
}