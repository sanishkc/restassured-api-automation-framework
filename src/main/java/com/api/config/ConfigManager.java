package com.api.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigManager
 *
 * Responsible for:
 *  - Loading environment-specific configuration files
 *  - Providing configuration values across the framework
 *
 * Implemented using Singleton pattern to ensure
 * properties are loaded only once per execution.
 */
public class ConfigManager {

    private static Properties properties;

    // Private constructor to prevent object creation
    private ConfigManager() {
    }

    /**
     * Loads the configuration file based on the environment.
     * Environment can be passed using:
     * -Denv=qa / -Denv=uat / -Denv=prod
     *
     * Defaults to 'qa' if no environment is provided.
     */
    private static void loadProperties() {

        if (properties == null) {
            properties = new Properties();
            String env = System.getProperty("env", "qa");

            try (FileInputStream fis =
                         new FileInputStream("src/test/resources/config-" + env + ".properties")) {

                properties.load(fis);

            } catch (IOException e) {
                throw new RuntimeException(
                        "Unable to load configuration for environment: " + env, e);
            }
        }
    }

    /**
     * Returns the value for the given key from the configuration file.
     *
     * @param key Configuration property key
     * @return Property value
     */
    public static String getProperty(String key) {
        loadProperties();
        return properties.getProperty(key);
    }
}
