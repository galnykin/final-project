package ru.kupibilet.api.clients;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

/**
 * Abstract base class for API clients.
 * Provides common request configuration, logging, and helper methods for HTTP operations.
 * Intended to be extended by specific API clients such as {@code AuthClient}, {@code SearchClient}, etc.
 */
public abstract class BaseApiClient {

    /**
     * Logger instance that dynamically reflects the subclass name.
     * Useful for consistent logging across all API clients.
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Builds a base request specification with JSON headers.
     * Can be extended or overridden by subclasses to add authentication or custom headers.
     *
     * @return a configured {@link RequestSpecification} with content type and accept headers set to JSON
     */
    protected RequestSpecification baseRequest() {
        return given()
                .contentType("application/json")
                .accept("application/json");
    }

    /**
     * Sends a POST request to the specified URL with the given request body.
     * Logs the request details before execution.
     *
     * @param url  the endpoint URL
     * @param body the request payload object
     * @return the {@link Response} returned by the server
     */
    protected Response post(String url, Object body) {
        log.info("POST to {} with body: {}", url, body);
        return baseRequest().body(body).post(url);
    }

    /**
     * Sends a GET request to the specified URL.
     * Logs the request details before execution.
     *
     * @param url the endpoint URL
     * @return the {@link Response} returned by the server
     */
    protected Response get(String url) {
        log.info("GET from {}", url);
        return baseRequest().get(url);
    }

    /**
     * Logs the status code and formatted response body.
     * Useful for debugging and reporting in test scenarios.
     *
     * @param response the {@link Response} object to log
     */
    protected void logResponse(Response response) {
        log.info("Status: {}, Body: {}", response.statusCode(), response.body().asPrettyString());
    }
}
