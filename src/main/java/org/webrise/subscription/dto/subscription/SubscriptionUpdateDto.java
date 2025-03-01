package org.webrise.subscription.dto.subscription;

import java.math.BigDecimal;

public record SubscriptionUpdateDto(
    Long id,
    String name,
    String description,
    BigDecimal price,
    Integer durationDays
) {
}
