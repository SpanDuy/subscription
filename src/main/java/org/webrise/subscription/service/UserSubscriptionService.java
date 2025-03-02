package org.webrise.subscription.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webrise.subscription.exception.ConflictException;
import org.webrise.subscription.exception.NotFoundException;
import org.webrise.subscription.model.User;
import org.webrise.subscription.model.UserSubscription;
import org.webrise.subscription.model.Subscription;
import org.webrise.subscription.model.SubscriptionStatus;
import org.webrise.subscription.repository.UserRepository;
import org.webrise.subscription.repository.UserSubscriptionRepository;
import org.webrise.subscription.repository.SubscriptionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSubscriptionService {
    
    private final UserSubscriptionRepository userSubscriptionRepository;

    public UserSubscription getUserSubscriptionById(Long id) {
        return userSubscriptionRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User subscription not found with id: " + id));
    }

    private Boolean isSubscriptionExists(User user, Subscription subscription) {
        return userSubscriptionRepository.existsByUserAndSubscription(user, subscription);
    }

    public UserSubscription subscribeUserToSubscription(User user, Subscription subscription) {
        if (isSubscriptionExists(user, subscription)) {
            throw new ConflictException("User already has an active subscription to this subscription");
        }

        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plusDays(subscription.getDurationDays());
            
        UserSubscription userSubscription = UserSubscription.builder()
            .user(user)
            .subscription(subscription)
            .startDate(startDate)
            .endDate(endDate)
            .status(SubscriptionStatus.ACTIVE)
            .build();
            
        return userSubscriptionRepository.save(userSubscription);
    }

    public List<UserSubscription> getUserSubscriptions(User user) {
        return userSubscriptionRepository.findByUser(user);
    }

    @Transactional
    public void cancelUserSubscription(User user, Subscription subscription) {
        userSubscriptionRepository.cancelSubscriptionByUserAndSubscription(user, subscription);
    }

    public void checkExpiredSubscriptions() {
        LocalDateTime now = LocalDateTime.now();
        List<UserSubscription> expiredSubscriptions = userSubscriptionRepository.findByEndDateBefore(now);
        
        expiredSubscriptions.stream()
                .filter(sub -> SubscriptionStatus.ACTIVE.equals(sub.getStatus()))
                .forEach(sub -> {
                    sub.setStatus(SubscriptionStatus.EXPIRED);
                    userSubscriptionRepository.save(sub);
                });
    }
} 