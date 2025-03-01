package org.webrise.subscription.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Subscription extends ExtendedEntity {
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    
    @Column(name = "duration_days", nullable = false)
    private Integer durationDays;
    
    @OneToMany(mappedBy = "subscription")
    @Builder.Default
    private Set<UserSubscription> userSubscriptions = new HashSet<>();
} 