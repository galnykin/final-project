package api;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.kupibilet.api.clients.AuthClient;
import ru.kupibilet.api.models.LoginRequest;
import ru.kupibilet.ui.utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Авторизация")
@Feature("API вход")
@Tag("api")
@Owner("sergey")
public class LoginApiTest extends BaseApiClient {

    @Story("Негативный сценарий: неверные email и pass")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("API: Ошибка при входе с неверными данными (ожидается 200)")
    public void testInvalidLoginReturns200() {
        String email = ConfigReader.get("invalid.email");
        String password = ConfigReader.get("invalid.password");

        logger.info("Sending login request with email='{}' and invalid password", email);
        LoginRequest request = new LoginRequest(email, password);

        var response = AuthClient.login(request);

        logger.info("Received response: status={}, body={}", response.statusCode(), response.body().asString());
        assertEquals(200, response.statusCode());
    }

    @Story("Позитивный сценарий: успешный вход")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("sergey")
    @Tag("api")
    @Disabled("This test is temporarily disabled")
    @Test
    @DisplayName("API: Успешный вход с валидными данными")
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
