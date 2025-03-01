package org.webrise.subscription.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User extends ExtendedEntity {
    
    @Column(name = "username", nullable = false)
    private String username;
    
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<UserSubscription> subscriptions = new HashSet<>();
} 