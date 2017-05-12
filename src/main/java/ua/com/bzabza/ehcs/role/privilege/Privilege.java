package ua.com.bzabza.ehcs.role.privilege;

import lombok.*;
import ua.com.bzabza.ehcs.role.Role;

import javax.persistence.*;
import java.util.Collection;

@RequiredArgsConstructor
@NoArgsConstructor
@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
