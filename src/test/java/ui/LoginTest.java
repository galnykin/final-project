package ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.kupibilet.ui.pages.app.HomePage;
import ru.kupibilet.ui.pages.app.LoginModal;
import ru.kupibilet.ui.utils.ConfigReader;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Authorization")
@Feature("Login Modal Window")
@Owner("sergey")
@Tag("ui")
public class LoginTest extends BaseTest {

    private HomePage homePage;
    private LoginModal loginModal;

    @BeforeEach
    public void init() {
        logger.info("Initializing page objects");
        homePage = new HomePage(driver);
        loginModal = new LoginModal(driver);
        logger.info("Opening login modal");
        homePage.clickLoginButton();
    }

    @Story("Negative scenario: incorrect email and password")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Error when logging in with invalid credentials")
    public void testInvalidEmailAndPasswordShowsErrorMessage() {
        logger.info("Entering invalid credentials");
        loginModal.enterEmail(ConfigReader.get("invalid.email"));
        loginModal.enterPassword(ConfigReader.get("invalid.password"));
        logger.info("Submitting login form with invalid credentials");
        loginModal.clickSubmit();

        assertEquals("Вы ошиблись в почте или пароле", loginModal.getAuthErrorMessageText());
    }

    @Story("Negative scenario: empty fields")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email and password fields are empty")
    public void testEmptyEmailAndPasswordShowErrorMessages() {
        logger.info("Submitting login form with empty fields");
        loginModal.clickSubmit();

        assertAll(
                () -> assertEquals("Введите вашу электронную почту", loginModal.getEmailErrorMessageText()),
                () -> assertEquals("Введите ваш пароль", loginModal.getPasswordErrorMessageText())
        );
    }

    @Story("Negative scenario: invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email format is invalid")
    public void testInvalidEmailFormatShowsErrorMessage() {
        logger.info("Entering invalid email format");
        loginModal.enterEmail("test.email");
        loginModal.enterPassword(ConfigReader.get("invalid.password"));
        logger.info("Submitting login form with invalid email format");
        loginModal.clickSubmit();

        assertEquals("Неверный формат электронной почты", loginModal.getEmailErrorMessageText());
    }
}
