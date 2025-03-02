package org.webrise.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.webrise.subscription.model.Subscription;

import java.math.BigDecimal;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByPriceLessThanEqual(BigDecimal price);
    List<Subscription> findByDurationDaysLessThanEqual(Integer days);
    List<Subscription> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT s FROM Subscription s " +
           "LEFT JOIN s.userSubscriptions us " +
           "GROUP BY s.id " +
           "ORDER BY COUNT(us) DESC " +
           "LIMIT :limit")
    List<Subscription> findTopPopular(@Param("limit") Integer limit);
} 