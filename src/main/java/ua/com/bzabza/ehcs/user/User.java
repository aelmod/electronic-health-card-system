package ua.com.bzabza.ehcs.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ua.com.bzabza.ehcs.UserEntity;
import ua.com.bzabza.ehcs.card.Card;
import ua.com.bzabza.ehcs.city.City;
import ua.com.bzabza.ehcs.country.Country;
import ua.com.bzabza.ehcs.role.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable, UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Integer id;

    @NonNull
    @Column(nullable = false)
    @JsonView(MinimalView.class)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    private String secret;

    @NonNull
    @JsonView(MinimalView.class)
    private String fullName;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String email;

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
    @JsonView(AllPrimitivesView.class)
    private String phone;

    @OneToOne(mappedBy = "patient")
    private Card card;

    @ManyToMany
    @JoinTable(name = "doctors_patients",
            joinColumns = {@JoinColumn(name = "doctor_id")},
            inverseJoinColumns = {@JoinColumn(name = "patient_id")}
    )
    private Set<User> doctorPatientRelationship = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles = Collections.emptyList();

    public interface MinimalView {}

    public interface AllPrimitivesView extends MinimalView {}

    public interface FullView extends AllPrimitivesView, Country.MinimumView, City.MinimumView {}

    public interface DoctorView extends MinimalView {}

    public interface PatientView extends MinimalView {}
}
