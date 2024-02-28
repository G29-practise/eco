package org.example.eco.user.role.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eco.user.entity.User;
import org.example.eco.user.permission.entity.Permission;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String name;

    @ManyToMany(mappedBy = "roles")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;
}
