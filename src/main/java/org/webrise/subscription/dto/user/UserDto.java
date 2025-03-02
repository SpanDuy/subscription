package org.webrise.subscription.dto.user;

import java.time.LocalDateTime;

public record UserDto(
    Long id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    String username,
    String email
) {
}
