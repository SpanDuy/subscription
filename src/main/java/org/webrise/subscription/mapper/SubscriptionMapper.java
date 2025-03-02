package org.webrise.subscription.mapper;


import java.util.List;

import org.springframework.stereotype.Component;
import org.webrise.subscription.dto.subscription.SubscriptionCreateDto;
import org.webrise.subscription.dto.subscription.SubscriptionDto;
import org.webrise.subscription.dto.subscription.SubscriptionListDto;
import org.webrise.subscription.dto.subscription.SubscriptionUpdateDto;
import org.webrise.subscription.model.Subscription;

@Component
public class SubscriptionMapper {

    public SubscriptionListDto toSubscriptionListDto(Subscription subscription) {
        return new SubscriptionListDto(
            subscription.getId(),
            subscription.getName(),
            subscription.getPrice(),
            subscription.getDurationDays()
        );
    }

    public List<SubscriptionListDto> toSubscriptionListDto(List<Subscription> subscriptions) {
        return subscriptions.stream()
            .map(this::toSubscriptionListDto)
            .toList();
    }

    public SubscriptionDto toSubscriptionDto(Subscription subscription) {
        return new SubscriptionDto(
            subscription.getId(),
            subscription.getCreatedAt(),
            subscription.getUpdatedAt(),
            subscription.getName(),
            subscription.getDescription(),
            subscription.getPrice(),
            subscription.getDurationDays()
        );
    }

    public Subscription toSubscription(SubscriptionCreateDto subscriptionCreateDto) {
        return Subscription.builder()
            .name(subscriptionCreateDto.name())
            .description(subscriptionCreateDto.description())
            .price(subscriptionCreateDto.price())
            .durationDays(subscriptionCreateDto.durationDays())
            .build();
    }

    public Subscription toSubscription(SubscriptionUpdateDto subscriptionUpdateDto) {
        return Subscription.builder()
            .id(subscriptionUpdateDto.id())
            .name(subscriptionUpdateDto.name())
            .description(subscriptionUpdateDto.description())
            .price(subscriptionUpdateDto.price())
            .durationDays(subscriptionUpdateDto.durationDays())
            .build();
    }

    public Subscription toSubscription(SubscriptionDto subscriptionDto) {
        return Subscription.builder()
            .id(subscriptionDto.id())
            .name(subscriptionDto.name())
            .description(subscriptionDto.description())
            .price(subscriptionDto.price())
            .durationDays(subscriptionDto.durationDays())
            .build();
    }
}