package com.api.models.response;

import lombok.Getter;

/**
 * UserProfileResponse represents the response payload returned by
 * the User Profile APIs (Get / Update).
 * This model is used to deserialize the JSON response
 * into a Java object for validation in test cases.
 */
@Getter
public class UserProfileResponse {

    // Unique identifier of the user
    private int id;

    // Username of the user account
    private String username;

    // Email address associated with the user
    private String email;

    // First name of the user
    private String firstName;

    // Last name of the user
    private String lastName;

    // Mobile number of the user
    private String mobileNumber;
}
