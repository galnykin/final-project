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
        homePage = new HomePage(driver);
        logger.info("Clicking login button on HomePage");
        homePage.clickLoginButton();

        logger.info("Opening login modal");
        loginModal = new LoginModal(driver);
    }

    @Story("Negative scenario: login attempt with valid but unregistered credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Error when logging in with valid but unregistered credentials")
    public void testUnregisteredUserLoginShowsErrorMessage() {
        loginModal.submitValidUnregisteredCredentials();
        assertEquals(LoginModal.WRONG_EMAIL_OR_PASSWORD_MESSAGE, loginModal.getAuthErrorMessageText());
    }

    @Story("Negative scenario: empty fields")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email and password fields are empty")
    public void testEmptyEmailAndPasswordShowErrorMessages() {
        loginModal.clickSubmitButton();
        assertAll(
                () -> assertEquals(LoginModal.EMAIL_REQUIRED_MESSAGE, loginModal.getEmailErrorMessageText()),
                () -> assertEquals(LoginModal.PASSWORD_REQUIRED_MESSAGE, loginModal.getPasswordErrorMessageText())
        );
    }

    @Story("Negative scenario: invalid email format")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email format is invalid")
    public void testInvalidEmailFormatShowsErrorMessage() {
        loginModal.submitInvalidEmailFormat();
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }
}
