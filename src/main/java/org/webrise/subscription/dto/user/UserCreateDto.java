package org.webrise.subscription.dto.user;

import jakarta.validation.constraints.Email;

public record UserCreateDto(
    String username,
    @Email
    String email
) {
}

