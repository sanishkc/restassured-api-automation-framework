package com.api.models.request;

import lombok.Builder;
import lombok.Getter;

/**
 * UserProfileRequest represents the request payload used to
 * update a user's profile information via the User Profile API.
 * This model is serialized into JSON format when invoking
 * the update profile endpoint.
 */
@Getter
@Builder
public class UserProfileRequest {

    // Updated first name of the user
    private String firstName;

    // Updated last name of the user
    private String lastName;

    // Updated email address of the user
    private String email;

    // Updated mobile number of the user
    private String mobileNumber;
}
