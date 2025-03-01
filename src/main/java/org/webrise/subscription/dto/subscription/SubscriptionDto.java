package org.webrise.subscription.dto.subscription;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SubscriptionDto(
    Long id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String name,
    BigDecimal price,
    Integer durationDays
) {
}