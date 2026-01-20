package com.api.service;

import com.api.models.request.UserLoginRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

/**
 * AuthenticationService
 *
 * Enterprise-grade service class for authentication APIs.
 */
public class AuthenticationService {

    /**
     * Performs user login using a strongly-typed request model.
     *
     * @param request UserLoginRequest payload
     * @return API response
     */
    public Response userLogin(UserLoginRequest request) {

        return RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(request)
                .when()
                    .post("/api/auth/login")
                .then()
                    .log().ifValidationFails()
                    .extract()
                    .response();
    }
}
