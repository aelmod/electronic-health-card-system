package ua.com.bzabza.ehcs.patient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ua.com.bzabza.ehcs.UserEntity;
import ua.com.bzabza.ehcs.city.City;
import ua.com.bzabza.ehcs.country.Country;
import ua.com.bzabza.ehcs.patient.card.Card;
import ua.com.bzabza.ehcs.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "patients")
public class Patient implements Serializable, UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(AllPrimitivesView.class)
    private Integer id;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String login;

    @NonNull
    @Column(nullable = false)
    private String password;

    private String secret;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String fullName;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthday;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date registerDate;

    public enum Sex {
        MALE, FEMALE;
    }

    @NonNull
    @JsonView(AllPrimitivesView.class)
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @NonNull
    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @NonNull
    @JsonView(FullView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonView(FullView.class)
    private User user;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String phone;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String email;

    @OneToOne(mappedBy = "patient")
    private Card card;

//    @JsonView(AllPrimitivesView.class)
//    private String photo;

    public interface AllPrimitivesView {
    }

    public interface FullView extends AllPrimitivesView, User.MinimalView, Country.MinimumView, City.MinimumView {
    }
}
