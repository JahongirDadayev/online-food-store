package com.example.onlinefoodstore.model.entities;

import com.example.onlinefoodstore.model.annotations.SearchableField;
import com.example.onlinefoodstore.model.entities.base.BaseEntityUID;
import com.example.onlinefoodstore.model.enums.EAuthority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class User extends BaseEntityUID implements UserDetails {
    @SearchableField
    @Column(name = "first_name")
    private String firstName;

    @SearchableField
    @Column(name = "last_name")
    private String lastName;

    @SearchableField
    @Column(name = "username", updatable = false)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "authority")
    @CollectionTable(
            name = "users_authorities",
            joinColumns = {@JoinColumn(name = "user_id")}
    )
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<EAuthority> authorities;

    @Column(name = "enable")
    private boolean enabled = true;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
