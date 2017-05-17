package ua.com.bzabza.ehcs.role;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import ua.com.bzabza.ehcs.role.privilege.Privilege;
import ua.com.bzabza.ehcs.user.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(MinimalView.class)
    private Integer id;

    @NonNull
    @JsonView(MinimalView.class)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = Collections.emptyList();

    @ManyToMany
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges = Collections.emptyList();

    @Override
    public String getAuthority() {
        return name;
    }

    public interface MinimalView {}
}
