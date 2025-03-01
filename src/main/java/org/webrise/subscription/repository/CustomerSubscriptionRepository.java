package org.webrise.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.webrise.subscription.model.SubscriptionStatus;
import org.webrise.subscription.model.User;
import org.webrise.subscription.model.UserSubscription;
import org.webrise.subscription.model.Subscription;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public interface CustomerSubscriptionRepository extends JpaRepository<UserSubscription, Long> {
    List<UserSubscription> findByUser(User user);
    List<UserSubscription> findBySubscription(Subscription subscription);
    List<UserSubscription> findByStatus(SubscriptionStatus status);
    List<UserSubscription> findByEndDateBefore(LocalDateTime date);
    
    @Query("SELECT cs FROM UserSubscription cs WHERE cs.user.id = :userId AND cs.status = 'ACTIVE'")
    List<UserSubscription> findActiveSubscriptionsByUserId(Long userId);
} 