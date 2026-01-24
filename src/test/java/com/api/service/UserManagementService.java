package com.api.service;

import com.api.models.request.UserProfileRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * UserManagementService handles all user profile–related API interactions.
 * This service layer abstracts RestAssured calls from test classes
 * and provides reusable methods for user profile operations.
 */
public class UserManagementService {

    /**
     * Retrieves the logged-in user's profile details.
     *
     * @param token Authentication token received from login API
     * @return Response object containing user profile information
     */
    public Response getUserProfile(String token) {

        // Prepare and execute GET request to fetch user profile
        return RestAssured
                .given()
                    .contentType(ContentType.JSON)              // Specify request content type
                    .header("Authorization", "Bearer " + token) // Attach Bearer token for authorization
                .when()
                    .get("/api/users/profile")                  // Invoke Get User Profile endpoint
                .then()
                    .statusCode(200)                             // Validate successful response
                    .log().ifValidationFails()                   // Log details only if validation fails
                    .extract()
                    .response();                                 // Extract and return response
    }

    /**
     * Updates the logged-in user's profile details.
     *
     * @param userProfileRequestPayload Request payload containing updated profile data
     * @param token Authentication token received from login API
     * @return Response object containing updated user profile information
     */
    public Response updateUserProfile(
            UserProfileRequest userProfileRequestPayload,
            String token) {

        // Prepare and execute PUT request to update user profile
        return RestAssured
                .given()
                    .contentType(ContentType.JSON)              // Specify request content type
                    .header("Authorization", "Bearer " + token) // Attach Bearer token for authorization
                    .body(userProfileRequestPayload)             // Attach update profile request payload
                .when()
                    .put("/api/users/profile")                  // Invoke Update User Profile endpoint
                .then()
                    .statusCode(200)                             // Validate successful update
                    .log().ifValidationFails()                   // Log details only if validation fails
                    .extract()
                    .response();                                 // Extract and return response
    }
}
