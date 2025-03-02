package org.webrise.subscription.dto.userSubscription;

import org.webrise.subscription.dto.subscription.SubscriptionListDto;
import org.webrise.subscription.model.SubscriptionStatus;

import java.time.LocalDateTime;

public record UserSubscriptionListDto(
    Long id,
    SubscriptionListDto subscription,
    LocalDateTime startDate,
    LocalDateTime endDate,
    SubscriptionStatus status
) {
}