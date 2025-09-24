package api;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import ru.kupibilet.api.clients.AuthClient;
import ru.kupibilet.api.models.LoginRequest;
import ru.kupibilet.ui.utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginApiTest {
    @Test
    public void testInvalidLoginReturns200() {

        LoginRequest request = new LoginRequest(
                ConfigReader.get("invalid.email"),
                ConfigReader.get("invalid.password"));

        var response = AuthClient.login(request);

        assertEquals(200, response.statusCode());
    }

    @Test
    public void testValidLogin() {
        LoginRequest request = new LoginRequest(
                ConfigReader.get("valid.email"),
                ConfigReader.get("valid.password"),
                ConfigReader.get("valid.version"));

        var response = AuthClient.login(request);

        SoftAssertions softly = new SoftAssertions();

        assertEquals(200, response.statusCode());

        String accountId = response.jsonPath().getString("data.account_id");
        softly.assertThat(accountId).isEqualTo(ConfigReader.get("valid.account_id"));

        String authToken = response.jsonPath().getString("data.auth_token");
        softly.assertThat(authToken)
                .withFailMessage("auth_token должен быть в ответе")
                .isNotEmpty();

        softly.assertAll();
    }
}
