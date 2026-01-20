package com.api.models.response;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString(exclude = "token")
@JsonDeserialize(builder = UserLoginResponse.UserLoginResponseBuilder.class)
public class UserLoginResponse {

    @JsonProperty("token")
    private String token;

    @JsonProperty("type")
    private String type;

    @JsonProperty("id")
    private int id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;

    @JsonProperty("roles")
    private List<String> roles;

    // Jackson-friendly builder
    @JsonPOJOBuilder(withPrefix = "")
    public static class UserLoginResponseBuilder {}
}
