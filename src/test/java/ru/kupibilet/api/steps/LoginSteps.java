package ru.kupibilet.api.steps;

import io.restassured.response.Response;
import ru.kupibilet.api.clients.AuthClient;
import ru.kupibilet.api.models.LoginRequest;
import ru.kupibilet.auth.dto.Credentials;
import ru.kupibilet.utils.api.ApiLogUtils;

/**
 * Encapsulates the login flow for API tests.
 * Provides a fluent interface for submitting credentials and accessing the response.
 */
public class LoginSteps {

    private Credentials credentials;
    private LoginRequest request;
    private Response response;
    private final AuthClient authClient = new AuthClient();

    public static LoginSteps with(Credentials credentials) {
        LoginSteps steps = new LoginSteps();
        steps.credentials = credentials;
        steps.request = LoginRequest.from(credentials);
        return steps;
    }

    public LoginSteps submit() {
        ApiLogUtils.logLoginAttempt(credentials);
        response = authClient.login(request);
        ApiLogUtils.logLoginResponse(response);
        return this;
    }

    public Response response() {
        return response;
    }

    public Credentials credentials() {
        return credentials;
    }
}
