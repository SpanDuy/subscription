package org.webrise.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.webrise.subscription.model.SubscriptionStatus;
import org.webrise.subscription.model.User;
import org.webrise.subscription.model.UserSubscription;
import org.webrise.subscription.model.Subscription;

import java.time.LocalDateTime;
import java.util.List;

public interface UserSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findByUser(User user);
    List<UserSubscription> findBySubscription(Subscription subscription);
    List<UserSubscription> findByStatus(SubscriptionStatus status);
    List<UserSubscription> findByEndDateBefore(LocalDateTime date);
    
    @Query("SELECT us FROM UserSubscription us WHERE us.user.id = :userId AND us.status = 'ACTIVE'")
    List<UserSubscription> findActiveSubscriptionsByUserId(@Param("userId") Long userId);
    
    @Modifying
    @Query("UPDATE UserSubscription us " +
            "SET us.status = 'CANCELLED' " +
            "WHERE us.user = :user " +
            "AND us.subscription = :subscription"
    )
    void cancelSubscriptionByUserAndSubscription(
            @Param("user") User user,
            @Param("subscription") Subscription subscription
    );

    Boolean existsByUserAndSubscription(User user, Subscription subscription);
} 