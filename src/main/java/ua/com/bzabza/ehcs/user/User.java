package ua.com.bzabza.ehcs.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ua.com.bzabza.ehcs.UserEntity;
import ua.com.bzabza.ehcs.patient.Patient;
import ua.com.bzabza.ehcs.role.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
    @JsonView(MinimalView.class)
    private String fullName;

    @NonNull
    @JsonView(AllPrimitivesView.class)
    private String email;

    @NonNull
    @Column(nullable = false)
    @JsonView(MinimalView.class)
    private String username;

    @NonNull
    @Column(nullable = false)
    private String password;

    private String secret;

    @OneToMany(mappedBy = "user")
    private List<Patient> patients = new ArrayList<>();

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

    public interface FullView extends AllPrimitivesView {}
}
