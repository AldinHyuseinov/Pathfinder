package bg.softuni.pathfinder.models.entities;

import bg.softuni.pathfinder.models.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class Role extends BaseEntity {
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType name;

    @ManyToMany
    @JoinTable(name = "users_roles")
    private Set<User> users;
}
