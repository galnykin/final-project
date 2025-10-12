package ru.kupibilet.utils.api;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.auth.dto.Credentials;

/**
 * Utility class for logging API request and response details.
 */
public class ApiLogUtils {

    private static final Logger log = LoggerFactory.getLogger(ApiLogUtils.class);

    /**
     * Logs the login attempt with provided credentials.
     *
     * @param credentials the login credentials
     */
    public static void logLoginAttempt(Credentials credentials) {
        log.info("Sending login request with email='{}' and password='{}'",
                credentials.getEmail(), credentials.getPassword());
    }

    /**
     * Logs the response received from the login API.
     *
     * @param response the HTTP response
     */
    public static void logLoginResponse(Response response) {
        log.info("Received response: status={}, body={}",
                response.statusCode(), response.body().asString());
    }
}
