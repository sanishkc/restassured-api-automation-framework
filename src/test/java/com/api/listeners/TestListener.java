package com.api.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestListener
 *
 * TestNG listener implementation to capture and log
 * test execution lifecycle events.
 *
 * This listener provides visibility into:
 *  - Test suite start and completion
 *  - Individual test start, success, failure, and skip events
 *
 * Useful for debugging, reporting, and CI/CD pipeline monitoring.
 */
public class TestListener implements ITestListener {
	
	 // Logger instance for test execution logs
	private static final Logger logger = LogManager.getLogger(TestListener.class);

	/**
     * Invoked before the test suite execution starts.
     *
     * @param context TestNG execution context
     */
	public void onStart(ITestContext context) {
		logger.info("Test Suite Started!!!");
	}

	/**
     * Invoked each time a test method is about to start.
     *
     * @param result Test method execution result
     */
	public void onTestStart(ITestResult result) {
		logger.info("Test Started: "+result.getMethod().getMethodName());
	}

	/**
     * Invoked when a test method completes successfully.
     *
     * @param result Test method execution result
     */
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Passed: "+result.getMethod().getMethodName());
	}

	 /**
     * Invoked when a test method fails.
     *
     * @param result Test method execution result
     */
	public void onTestFailure(ITestResult result) {
		logger.error("Test Failed: "+result.getMethod().getMethodName());
	}

	 /**
     * Invoked when a test method is skipped.
     *
     * @param result Test method execution result
     */
	public void onTestSkipped(ITestResult result) {
		logger.info("Test Skipped: "+result.getMethod().getMethodName());
	}

	/**
     * Invoked after all tests in the suite have finished execution.
     *
     * @param context TestNG execution context
     */
	public void onFinish(ITestContext context) {
		logger.info("Test Suite Completed!!!");
	}



}
