package com.api.service;

import com.api.models.request.SignupRequest;
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
     * @param request UserLoginRequest requestPayload
     * @return API response
     */
    public Response userLogin(UserLoginRequest requestPayload) {

        return RestAssured
                .given()
                    .contentType(ContentType.JSON)
                    .body(requestPayload)
                .when()
                    .post("/api/auth/login")
                .then()
                	.statusCode(200)
                    .log().ifValidationFails()
                    .extract()
                    .response();
    }
    
    /**
     * Performs user signup using a strongly-typed request model.
     *
     * @param request SignupRequest requestPayload
     * @return API response
     */
    public Response userSignup(SignupRequest requestPayload) {
    	
    	// Prepare the REST request using RestAssured
        // Set the content type as JSON and attach the signup request payload
    	return RestAssured
    			.given()
    				.contentType(ContentType.JSON)
    				.body(requestPayload)
    			.when()
    				.post("/api/auth/signup")
    			.then()
    				.statusCode(200)
    				.log().ifValidationFails()
    				.extract()
    				.response();		
    }
}
