package org.webrise.subscription.dto.subscription;

import java.math.BigDecimal;

public record SubscriptionListDto(
    Long id,
    String name,
    BigDecimal price,
    Integer durationDays
) {
}