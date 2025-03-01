package org.webrise.subscription.dto.user;

import java.time.LocalDateTime;

public record UserListDto(
    Long id,
    String username,
    String email
) {
}
