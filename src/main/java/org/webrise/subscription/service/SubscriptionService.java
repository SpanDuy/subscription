package org.webrise.subscription.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webrise.subscription.exception.NotFoundException;
import org.webrise.subscription.model.Subscription;
import org.webrise.subscription.repository.SubscriptionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    
    private final SubscriptionRepository subscriptionRepository;
    
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }
    
    public Subscription getSubscriptionById(Long id) {
        return subscriptionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Subscription not found with id: " + id));
    }
    
    public Subscription createSubscription(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }
    
    public Subscription updateSubscription(Long id, Subscription subscription) {
        Subscription existingSubscription = getSubscriptionById(id);
        existingSubscription.setName(subscription.getName());
        existingSubscription.setDescription(subscription.getDescription());
        existingSubscription.setPrice(subscription.getPrice());
        existingSubscription.setDurationDays(subscription.getDurationDays());
        return subscriptionRepository.save(existingSubscription);
    }
    
    public void deleteSubscription(Long id) {
        Subscription subscription = getSubscriptionById(id);
        subscriptionRepository.delete(subscription);
    }
    
    public List<Subscription> findByPriceLessThanEqual(BigDecimal price) {
        return subscriptionRepository.findByPriceLessThanEqual(price);
    }
    
    public List<Subscription> findByDurationDaysLessThanEqual(Integer days) {
        return subscriptionRepository.findByDurationDaysLessThanEqual(days);
    }
    
    public List<Subscription> findByNameContaining(String name) {
        return subscriptionRepository.findByNameContainingIgnoreCase(name);
    }
} 