package org.webrise.subscription.dto.user;

import jakarta.validation.constraints.Email;

public record UserUpdateDto(
    Long id,
    String username,
    @Email
    String email
) {
}
