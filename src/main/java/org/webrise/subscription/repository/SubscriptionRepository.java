package org.webrise.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webrise.subscription.model.Subscription;

import java.math.BigDecimal;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByPriceLessThanEqual(BigDecimal price);
    List<Subscription> findByDurationDaysLessThanEqual(Integer days);
    List<Subscription> findByNameContainingIgnoreCase(String name);
} 