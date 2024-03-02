package org.example.eco.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.eco.address.entity.Address;
import org.example.eco.order.entity.Order;
import org.example.eco.rating.entity.Rating;
import org.example.eco.user.permission.entity.Permission;
import org.example.eco.user.role.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    private UUID id;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String email;
    private LocalDateTime created;
    private LocalDateTime updated;
//    private Cart cart;
//    private Set<Card> cards;
//    private Set<Order> orders;
//    private Address address;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_permission",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Rating> ratings;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Order> orders;

    @OneToOne(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Stream<Permission> rolePermissionStream = roles.stream()
                .map(Role::getPermissions)
                .flatMap(Collection::stream);

        Stream<Permission> permissionStream = Stream.concat(rolePermissionStream, permissions.stream());

        Set<SimpleGrantedAuthority> collect = permissionStream
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toSet());

        return collect;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
