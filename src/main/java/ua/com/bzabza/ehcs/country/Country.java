package ua.com.bzabza.ehcs.country;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.com.bzabza.ehcs.city.City;
import ua.com.bzabza.ehcs.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "countries")
public class Country implements Serializable {

    public Country(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimumView.class)
    private Integer id;

    @JsonView(MinimumView.class)
    private String name;

    @OneToMany(mappedBy = "country")
    @JsonView(MinimumView.class)
    public List<City> cities = new ArrayList<>();

    @OneToMany(mappedBy = "country")
    @JsonView(WithUsers.class)
    private Set<User> patients = new HashSet<>();

    public interface WithUsers extends MinimumView, User.AllPrimitivesView {}

    public interface MinimumView extends City.MinimumView {}
}
