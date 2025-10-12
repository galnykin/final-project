package ru.kupibilet.api.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.api.models.LoginRequest;
import io.restassured.response.Response;
import ru.kupibilet.config.ApiConfig;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private final String url;
    private static final Logger log = LoggerFactory.getLogger(AuthClient.class);

    public AuthClient() {
        this.url = ApiConfig.getLoginApiUrl();
    }
    /**
     * Sends an authorization request to the login API endpoint.
     *
     * @param request the login request containing email and password
     * @return the HTTP response from the login endpoint
     */
    public Response login(LoginRequest request) {
        log.info("Sending authorization request for email: {}", request.getEmail());
        try {
            return given()
                    .contentType("application/json")
                    .body(request)
                    .post(url);
        } catch (Exception e) {
            log.error("Login request failed", e);
            throw new RuntimeException("Login API call failed", e);
        }
    }
}
