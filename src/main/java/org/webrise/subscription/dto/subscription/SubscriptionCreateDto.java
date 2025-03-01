package org.webrise.subscription.dto.subscription;

import java.math.BigDecimal;

public record SubscriptionCreateDto(
    String name,
    String description,
    BigDecimal price,
    Integer durationDays
) {
}
