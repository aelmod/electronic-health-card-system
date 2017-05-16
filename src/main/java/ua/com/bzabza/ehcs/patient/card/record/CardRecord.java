package ua.com.bzabza.ehcs.patient.card.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.bzabza.ehcs.patient.card.Card;
import ua.com.bzabza.ehcs.patient.card.record.commentary.Commentary;
import ua.com.bzabza.ehcs.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "card_records")
public class CardRecord implements Serializable {

    @JsonView(MinimalView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonView(MinimalView.class)
    private String text;

    @JsonView(MinimalView.class)
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date creationTime;

    @JsonView(WithCard.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @OneToMany(mappedBy = "cardRecord")
    @JsonView(FullView.class)
    private List<Commentary> commentaries = new ArrayList<>();

    @JsonView(MinimalView.class)
    @ManyToMany
    @JoinTable(name = "card_record_user",
            joinColumns = {@JoinColumn(name = "card_record_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users = new ArrayList<>();
//    private User user;

    public interface MinimalView {}

    public interface WithCard extends MinimalView, Card.FullView {}

    public interface FullView extends WithCard {}
}
