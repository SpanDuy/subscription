package org.webrise.subscription.service;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.webrise.subscription.exception.ConflictException;
import org.webrise.subscription.exception.NotFoundException;
import org.webrise.subscription.model.Subscription;
import org.webrise.subscription.model.User;
import org.webrise.subscription.model.UserSubscription;
import org.webrise.subscription.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final UserSubscriptionService userSubscriptionService;
    private final SubscriptionService subscriptionService;
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }
    
    public User createUser(User user) {
        if (existsByEmail(user.getEmail())) {
            throw new ConflictException("User with email " + user.getEmail() + " already exists");
        }
        return userRepository.save(user);
    }
    
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        
        if (!user.getEmail().equals(userDetails.getEmail())) {
            if (existsByEmail(userDetails.getEmail())) {
                throw new ConflictException("User with email " + userDetails.getEmail() + " already exists");
            }
        }
        
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }
    
    public void deleteUser(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
    
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User addSubscription(Long userId, Long subscriptionId) {
        User user = getUserById(userId);
        Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId);
        UserSubscription userSubscription = userSubscriptionService.subscribeUserToSubscription(user, subscription);
        user.getSubscriptions().add(userSubscription);
        return userRepository.save(user);
    }

    public List<UserSubscription> getUserSubscriptions(Long userId) {
        User user = getUserById(userId);
        return userSubscriptionService.getUserSubscriptions(user);
    }

    public void cancelUserSubscription(Long userId, Long subscriptionId) {
        User user = getUserById(userId);
        Subscription subscription = subscriptionService.getSubscriptionById(subscriptionId);
        userSubscriptionService.cancelUserSubscription(user, subscription);
    }
} 