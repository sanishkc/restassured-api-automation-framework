package com.api.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

/**
 * LoggingFilter
 *
 * Custom RestAssured filter used to log HTTP request and response details.
 * This filter is applied globally and helps in:
 *  - Debugging API failures
 *  - Tracing request/response flow
 *  - Improving test observability in CI/CD pipelines
 */
public class LoggingFilter implements Filter {

	// Logger instance for structured request and response logging
	private static final Logger logger = LogManager.getLogger(LoggingFilter.class);

	 /**
     * Intercepts the request and response during API execution.
     */
	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {

		// Log outgoing request details
		logRequest(requestSpec);

		// Continue execution of the request
		Response response = ctx.next(requestSpec, responseSpec);

		// Log incoming response details
		logResponse(response);

		return response;
	}

	/**
     * Logs HTTP request details such as method, URI, and headers.
     */
	private void logRequest(FilterableRequestSpecification requestSpec) {
		
		logger.info("Request: {} {}", requestSpec.getMethod(), requestSpec.getURI());
		logger.info("Headers: {}", requestSpec.getHeaders());
	}

	/**
     * Logs HTTP response details such as status code and body.
     */
	private void logResponse(Response response) {
		
		logger.info("Response Status: {}", response.getStatusCode());
		
		// Log response body only if present to avoid unnecessary exceptions
		if (response.getBody() != null) {
            logger.info("Response Body: {}", response.getBody().asString());
        }
	}
}
