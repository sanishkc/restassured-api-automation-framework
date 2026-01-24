package com.api.models.request;

import lombok.Builder;
import lombok.Getter;

/**
 * SignupRequest represents the request payload for the user signup API.
 * This model is used to serialize user signup data into JSON format
 * when invoking the /api/auth/signup endpoint.
 */
@Builder
@Getter
public class SignupRequest {
	
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String mobileNumber;

}
