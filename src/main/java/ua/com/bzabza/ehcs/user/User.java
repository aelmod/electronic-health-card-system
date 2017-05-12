package ua.com.bzabza.ehcs.user;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ua.com.bzabza.ehcs.role.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements Serializable {

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

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    public interface MinimalView {}

    public interface AllPrimitivesView extends MinimalView {}

    public interface FullView extends AllPrimitivesView {}
}
