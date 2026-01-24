package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.BaseTest;
import com.api.config.ConfigManager;
import com.api.models.request.UserLoginRequest;
import com.api.models.request.UserProfileRequest;
import com.api.models.response.UserLoginResponse;
import com.api.models.response.UserProfileResponse;
import com.api.service.AuthenticationService;
import com.api.service.UserManagementService;

import io.restassured.response.Response;

/**
 * UserManagementTest contains API test cases related to user profile
 * management such as retrieving and updating user profile details.
 */
@Listeners(com.api.listeners.TestListener.class)
public class UserManagementTest extends BaseTest {

    // Service class responsible for authentication-related API calls
    AuthenticationService authenticationService = new AuthenticationService();

    // Service class responsible for user management API calls
    UserManagementService userManagementService = new UserManagementService();

    /**
     * Test to verify the Get User Profile API.
     * Flow:
     * 1. Login using valid credentials
     * 2. Extract authentication token
     * 3. Invoke Get User Profile API
     * 4. Validate returned user profile details
     */
    @Test(description = "Verify get user profile API")
    public void verfityGetUserProfileTest() {

        // Fetch login credentials from configuration
        String username = ConfigManager.getProperty("login.username");
        String password = ConfigManager.getProperty("login.password");

        // Build login request payload
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username(username)
                .password(password)
                .build();

        // Invoke login API and capture response
        Response authenticationServiceResponse = authenticationService.userLogin(userLoginRequest);

        // Deserialize login response to extract authentication token
        UserLoginResponse userLoginResponse = authenticationServiceResponse.as(UserLoginResponse.class);

        // Invoke Get User Profile API using the authentication token
        Response userManagementServiceResponse =
                userManagementService.getUserProfile(userLoginResponse.getToken());

        // Deserialize profile response
        UserProfileResponse userProfileResponse =
                userManagementServiceResponse.as(UserProfileResponse.class);

        // Validate email returned in user profile
        Assert.assertEquals(
                userProfileResponse.getEmail(),
                "testuser101@test.com"
        );
    }

    /**
     * Test to verify the Update User Profile API.
     * Flow:
     * 1. Login using valid credentials
     * 2. Extract authentication token
     * 3. Update user profile details
     * 4. Validate updated profile information
     */
    @Test(description = "Verify update user profile API")
    public void verfityUpdateUserProfileTest() {

        // Fetch profile update login credentials from configuration
        String username = ConfigManager.getProperty("profile.username");
        String password = ConfigManager.getProperty("profile.password");

        // Build login request payload
        UserLoginRequest userLoginRequest = UserLoginRequest.builder()
                .username(username)
                .password(password)
                .build();

        // Invoke login API and capture response
        Response authenticationServiceResponse = authenticationService.userLogin(userLoginRequest);

        // Deserialize login response to extract authentication token
        UserLoginResponse userLoginResponse = authenticationServiceResponse.as(UserLoginResponse.class);

        // Build user profile update request payload
        UserProfileRequest userProfileRequest = UserProfileRequest.builder()
                .firstName("testuser201@test.com")
                .lastName("testuser201@test.com")
                .email("testuser201@test.com")
                .mobileNumber("8583918800")
                .build();

        // Invoke Update User Profile API using token
        Response userManagementServiceResponse =
                userManagementService.updateUserProfile(
                        userProfileRequest,
                        userLoginResponse.getToken()
                );

        // Deserialize updated profile response
        UserProfileResponse userProfileResponse =
                userManagementServiceResponse.as(UserProfileResponse.class);

        // Validate updated mobile number
        Assert.assertEquals(
                userProfileResponse.getMobileNumber(),
                "8583918800"
        );
    }
}