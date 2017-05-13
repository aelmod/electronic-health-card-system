package ua.com.bzabza.ehcs.city;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.bzabza.ehcs.country.Country;
import ua.com.bzabza.ehcs.patient.Patient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cities")
public class City implements Serializable {

    public City(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimumView.class)
    private Integer id;

    @JsonView(MinimumView.class)
    private String name;

    @ManyToOne
    @JsonView(FullView.class)
    private Country country;

    @OneToMany(mappedBy = "city")
    @JsonView(FullView.class)
    private Set<Patient> patients = new HashSet<>();

    public interface FullView extends MinimumView, Patient.AllPrimitivesView {
    }

    public interface MinimumView {
    }
}
