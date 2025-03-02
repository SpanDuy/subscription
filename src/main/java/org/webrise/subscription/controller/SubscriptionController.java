package org.webrise.subscription.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.webrise.subscription.dto.ResponseDto;
import org.webrise.subscription.dto.subscription.SubscriptionCreateDto;
import org.webrise.subscription.dto.subscription.SubscriptionDto;
import org.webrise.subscription.dto.subscription.SubscriptionListDto;
import org.webrise.subscription.dto.subscription.SubscriptionUpdateDto;
import org.webrise.subscription.mapper.SubscriptionMapper;
import org.webrise.subscription.model.Subscription;
import org.webrise.subscription.service.SubscriptionService;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {
    
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper subscriptionMapper;
    
    @GetMapping
    public ResponseDto<List<SubscriptionListDto>> getAllSubscriptions() {
        return ResponseDto.success(subscriptionMapper.toSubscriptionListDto(subscriptionService.getAllSubscriptions()));
    }
    
    @GetMapping("/{id}")
    public ResponseDto<SubscriptionDto> getSubscriptionById(@PathVariable Long id) {
        return ResponseDto.success(subscriptionMapper.toSubscriptionDto(subscriptionService.getSubscriptionById(id)));
    }
    
    @PostMapping
    public ResponseDto<SubscriptionDto> createSubscription(
            @Valid @RequestBody SubscriptionCreateDto subscriptionCreateDto
    ) {
        Subscription subscription = subscriptionMapper.toSubscription(subscriptionCreateDto);
        Subscription createdSubscription = subscriptionService.createSubscription(subscription);
        return ResponseDto.success(subscriptionMapper.toSubscriptionDto(createdSubscription));
    }
    
    @PutMapping("/{id}")
    public ResponseDto<SubscriptionDto> updateSubscription(
            @PathVariable Long id, 
            @Valid @RequestBody SubscriptionUpdateDto subscriptionUpdateDto
    ) {
        Subscription subscription = subscriptionMapper.toSubscription(subscriptionUpdateDto);
        Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscription);
        return ResponseDto.success(subscriptionMapper.toSubscriptionDto(updatedSubscription));
    }
    
    @DeleteMapping("/{id}")
    public ResponseDto<Void> deleteSubscription(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return ResponseDto.success(null, "Subscription deleted successfully");
    }

    @GetMapping("/top")
    public ResponseDto<List<SubscriptionListDto>> getTopSubscriptions(@RequestParam(defaultValue = "3") Integer limit) {
        return ResponseDto.success(subscriptionMapper.toSubscriptionListDto(subscriptionService.getTopSubscriptions(limit)));
    }
    
//    @GetMapping("/search/price")
//    public ResponseEntity<List<Subscription>> getSubscriptionsByMaxPrice(
//            @RequestParam BigDecimal maxPrice) {
//        return ResponseEntity.ok(subscriptionService.findByPriceLessThanEqual(maxPrice));
//    }
//
//    @GetMapping("/search/duration")
//    public ResponseEntity<List<Subscription>> getSubscriptionsByMaxDuration(
//            @RequestParam Integer maxDays) {
//        return ResponseEntity.ok(subscriptionService.findByDurationDaysLessThanEqual(maxDays));
//    }
//
//    @GetMapping("/search/name")
//    public ResponseEntity<List<Subscription>> getSubscriptionsByName(
//            @RequestParam String name) {
//        return ResponseEntity.ok(subscriptionService.findByNameContaining(name));
//    }
} 