package ua.com.bzabza.ehcs.microblog;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.bzabza.ehcs.patient.card.Card;
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
@Table(name = "microblogs")
public class Microblog implements Serializable {

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

    @JsonView(MinimalView.class)
    @ManyToMany
    @JoinTable(name = "microblog_user",
            joinColumns = {@JoinColumn(name = "microblog_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users = new ArrayList<>();
//    private User user;

    public interface MinimalView {
    }

    public interface WithCard extends MinimalView, Card.FullView {
    }

    public interface FullView extends WithCard {
    }
}
