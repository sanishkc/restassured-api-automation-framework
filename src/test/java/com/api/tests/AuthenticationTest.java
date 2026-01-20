package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.config.ConfigManager;
import com.api.models.request.UserLoginRequest;
import com.api.models.response.UserLoginResponse;
import com.api.service.AuthenticationService;

import io.restassured.response.Response;

/**
 * AuthenticationTest
 *
 * TestNG test class for authentication-related API tests.
 * Extends BaseTest to inherit RestAssured configuration
 * and uses a TestListener for logging test execution.
 */
@Listeners(com.api.listeners.TestListener.class)
public class AuthenticationTest extends BaseTest {
	
	// Service layer instance to interact with Authentication APIs
	AuthenticationService authenticationService = new AuthenticationService();
	
	/**
     * Test: Verify user login functionality
     *
     * Steps:
     *  1. Build login request payload
     *  2. Call login API
     *  3. Verify HTTP status code
     *  4. Deserialize response into UserLoginResponse
     *  5. Assert token is present and type is 'Bearer'
     */
	@Test(description = "Verify user login API")
	public void verifyUserLoginTest() {
		
		// Read credentials from config
        String username = ConfigManager.getProperty("login.username");
        String password = ConfigManager.getProperty("login.password");
		
		// Build the request payload using Builder pattern
		UserLoginRequest userLoginRequest = UserLoginRequest.builder()
				.username(username)
				.password(password)
				.build();
		
		// Call the login API
		Response response = authenticationService.userLogin(userLoginRequest);
		
		// Verify HTTP status code
		Assert.assertEquals(response.getStatusCode(), 200, "Expected status code 200");
		
		// Deserialize JSON response into UserLoginResponse POJO
		UserLoginResponse userLoginResponse = response.as(UserLoginResponse.class);
		
		// Assert token is not empty
		Assert.assertTrue(!userLoginResponse.getToken().isEmpty());
		
		// Assert token type is Bearer
		Assert.assertEquals(userLoginResponse.getType(), "Bearer");		
	}
}
