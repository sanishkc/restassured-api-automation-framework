package com.api.base;

import org.testng.annotations.BeforeClass;

import com.api.config.ConfigManager;
import com.api.filters.LoggingFilter;
import io.restassured.RestAssured;

/**
 * setUp()
 *
 * This method is executed once before any test methods in the test class.
 */
public class BaseTest {

	/**
     * Initializes RestAssured configuration before test execution.
     */
	@BeforeClass
	public void setUp() {

		// Define the base URI for all REST API requests
		//RestAssured.baseURI = "http://64.227.160.186:8080";
		RestAssured.baseURI = ConfigManager.getProperty("base.uri");

		// Register a custom logging filter to log request and response information
		RestAssured.filters(new LoggingFilter());
	}
}
