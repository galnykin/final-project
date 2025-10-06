package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.kupibilet.api.clients.AuthClient;
import ru.kupibilet.api.models.LoginRequest;
import ru.kupibilet.ui.utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Authorization")
@Feature("API Login")
@Tag("api")
@Owner("sergey")
public class LoginApiTest extends BaseApiClient {

    @Story("Negative scenario: incorrect email and password")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("API: Error when logging in with invalid credentials (expected 200)")
    public void testInvalidLoginReturns200() {
        String email = ConfigReader.get("invalid.email");
        String password = ConfigReader.get("invalid.password");

        logger.info("Sending login request with email='{}' and invalid password", email);
        LoginRequest request = new LoginRequest(email, password);

        var response = AuthClient.login(request);

        logger.info("Received response: status={}, body={}", response.statusCode(), response.body().asString());
        assertEquals(200, response.statusCode());
    }
}
