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
import ru.kupibilet.ui.pages.HomePage;
import ru.kupibilet.ui.pages.LoginModal;
import ru.kupibilet.ui.utils.ConfigReader;
import ru.kupibilet.ui.utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.kupibilet.ui.locators.LoginModalLocators.GENERAL_ERROR_MESSAGE;
import static ru.kupibilet.ui.locators.LoginModalLocators.EMAIL_ERROR_MESSAGE;
import static ru.kupibilet.ui.locators.LoginModalLocators.PASSWORD_ERROR_MESSAGE;

@Epic("Авторизация")
@Feature("Модальное окно входа")
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

    @Story("Негативный сценарий: неверные данные email и pass")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Ошибка при входе с неверными данными")
    public void testInvalidEmailAndPasswordShowsErrorMessage() {
        logger.info("Entering invalid credentials");
        loginModal.enterEmail(ConfigReader.get("invalid.email"));
        loginModal.enterPassword(ConfigReader.get("invalid.password"));
        logger.info("Submitting login form with invalid credentials");
        loginModal.clickSubmit();

        logger.info("Waiting for general error message");
        WaitUtils.waitForVisibility(driver, GENERAL_ERROR_MESSAGE);
        logger.info("Verifying general error message text");
        assertEquals("Вы ошиблись в почте или пароле", loginModal.getText(GENERAL_ERROR_MESSAGE));
    }

    @Story("Негативный сценарий: пустые поля")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Ошибка при пустых полях email и password")
    public void testEmptyEmailAndPasswordShowErrorMessages() {
        logger.info("Submitting login form with empty fields");
        loginModal.clickSubmit();

        logger.info("Waiting for email and password error messages");
        WaitUtils.waitForVisibility(driver, EMAIL_ERROR_MESSAGE);
        WaitUtils.waitForVisibility(driver, PASSWORD_ERROR_MESSAGE);

        logger.info("Verifying error messages for empty fields");
        assertAll(
                () -> assertEquals("Введите вашу электронную почту", loginModal.getText(EMAIL_ERROR_MESSAGE)),
                () -> assertEquals("Введите ваш пароль", loginModal.getText(PASSWORD_ERROR_MESSAGE))
        );
    }

    @Story("Негативный сценарий: неверный формат email")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Ошибка при вводе email в неверном формате")
    public void testInvalidEmailFormatShowsErrorMessage() {
        logger.info("Entering invalid email format");
        loginModal.enterEmail("test.email");
        loginModal.enterPassword(ConfigReader.get("invalid.password"));
        logger.info("Submitting login form with invalid email format");
        loginModal.clickSubmit();

        logger.info("Waiting for email format error message");
        WaitUtils.waitForVisibility(driver, EMAIL_ERROR_MESSAGE);
        logger.info("Verifying email format error message text");
        assertEquals("Неверный формат электронной почты", loginModal.getText(EMAIL_ERROR_MESSAGE));
    }
}
