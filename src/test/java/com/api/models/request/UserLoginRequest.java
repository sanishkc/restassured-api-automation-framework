package com.api.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * UserLoginRequest
 *
 * Immutable request model for user login API.
 * Uses Lombok for clean code and Builder pattern
 * for flexible object creation.
 */
@Getter
@Builder
@ToString(exclude = "password") // Prevent sensitive data logging
public class UserLoginRequest {

    @NotBlank(message = "Username must not be blank")
    @JsonProperty("username")
    private final String username;

    @NotBlank(message = "Password must not be blank")
    @JsonProperty("password")
    private final String password;
}
