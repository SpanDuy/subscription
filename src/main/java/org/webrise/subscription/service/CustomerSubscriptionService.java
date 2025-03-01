package org.webrise.subscription.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webrise.subscription.model.User;
import org.webrise.subscription.model.UserSubscription;
import org.webrise.subscription.model.Subscription;
import org.webrise.subscription.model.SubscriptionStatus;
import org.webrise.subscription.repository.UserRepository;
import org.webrise.subscription.repository.CustomerSubscriptionRepository;
import org.webrise.subscription.repository.SubscriptionRepository;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerSubscriptionService {
    
    private final CustomerSubscriptionRepository customerSubscriptionRepository;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;
    
    public List<UserSubscription> getAllCustomerSubscriptions() {
        return customerSubscriptionRepository.findAll();
    }
    
    public Optional<UserSubscription> getCustomerSubscriptionById(Long id) {
        return customerSubscriptionRepository.findById(id);
    }
    
    public List<UserSubscription> getSubscriptionsByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(customerSubscriptionRepository::findByUser)
                .orElse(List.of());
    }
    
    public List<UserSubscription> getActiveSubscriptionsByUserId(Long userId) {
        return customerSubscriptionRepository.findActiveSubscriptionsByUserId(userId);
    }
    
    public Optional<UserSubscription> subscribeUserToSubscription(Long userId, Long subscriptionId) {
        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Subscription> subscriptionOpt = subscriptionRepository.findById(subscriptionId);
        
        if (userOpt.isPresent() && subscriptionOpt.isPresent()) {
            User user = userOpt.get();
            Subscription subscription = subscriptionOpt.get();

            LocalDateTime startDate = LocalDateTime.now();
            LocalDateTime endDate = startDate.plusDays(subscription.getDurationDays());
            
            UserSubscription customerSubscription = new UserSubscription();
            customerSubscription.setUser(user);
            customerSubscription.setSubscription(subscription);
            customerSubscription.setStartDate(startDate);
            customerSubscription.setEndDate(endDate);
            customerSubscription.setStatus(SubscriptionStatus.ACTIVE);
            
            return Optional.of(customerSubscriptionRepository.save(customerSubscription));
        }
        
        return Optional.empty();
    }
    
    public Optional<UserSubscription> updateCustomerSubscription(Long id, UserSubscription details) {
        return customerSubscriptionRepository.findById(id).map(subscription -> {
            subscription.setStartDate(details.getStartDate());
            subscription.setEndDate(details.getEndDate());
            subscription.setStatus(details.getStatus());
            return customerSubscriptionRepository.save(subscription);
        });
    }
    
    public boolean cancelSubscription(Long id) {
        return customerSubscriptionRepository.findById(id).map(subscription -> {
            subscription.setStatus(SubscriptionStatus.CANCELLED);
            customerSubscriptionRepository.save(subscription);
            return true;
        }).orElse(false);
    }
    
    public void checkExpiredSubscriptions() {
        LocalDateTime now = LocalDateTime.now();
        List<UserSubscription> expiredSubscriptions = customerSubscriptionRepository.findByEndDateBefore(now);
        
        expiredSubscriptions.stream()
                .filter(sub -> SubscriptionStatus.ACTIVE.equals(sub.getStatus()))
                .forEach(sub -> {
                    sub.setStatus(SubscriptionStatus.EXPIRED);
                    customerSubscriptionRepository.save(sub);
                });
    }
} 