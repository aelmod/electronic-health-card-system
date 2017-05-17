package ua.com.bzabza.ehcs.card;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import ua.com.bzabza.ehcs.card.record.Record;
import ua.com.bzabza.ehcs.user.User;

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
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "card_id")
    )
    @JsonView(FullView.class)
    private User patient;

    @OneToMany(mappedBy = "card")
    @JsonView(FullView.class)
    private List<Record> records = new ArrayList<>();

    private String patientInfo;

    public interface MinimalView {}

    public interface FullView extends MinimalView, User.FullView, Record.MinimalView {}
}