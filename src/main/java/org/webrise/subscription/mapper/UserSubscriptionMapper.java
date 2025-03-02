package org.webrise.subscription.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webrise.subscription.dto.userSubscription.UserSubscriptionListDto;
import org.webrise.subscription.model.UserSubscription;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSubscriptionMapper {

    private final SubscriptionMapper subscriptionMapper;

    public UserSubscriptionListDto toUserSubscriptionListDto(UserSubscription userSubscription) {
        return new UserSubscriptionListDto(
            userSubscription.getId(),
            subscriptionMapper.toSubscriptionListDto(userSubscription.getSubscription()),
            userSubscription.getStartDate(),
            userSubscription.getEndDate(),
            userSubscription.getStatus()
        );
    }

    public List<UserSubscriptionListDto> toUserSubscriptionListDto(List<UserSubscription> userSubscriptions) {
        return userSubscriptions.stream()
            .map(this::toUserSubscriptionListDto)
            .toList();
    }
}