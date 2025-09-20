package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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

public class LoginTest extends BaseTest {

    private HomePage homePage;
    private LoginModal loginModal;

    @BeforeEach
    public void init() {
        homePage = new HomePage(driver);
        loginModal = new LoginModal(driver);
        homePage.clickLoginButton();
    }

    @Test
    @DisplayName("Ошибка при входе с неверными данными")
    public void testInvalidEmailAndPasswordShowsErrorMessage() {
        loginModal.enterEmail(ConfigReader.get("invalid.email"));
        loginModal.enterPassword(ConfigReader.get("invalid.password"));
        loginModal.clickSubmit();

        WaitUtils.waitForVisibility(driver, GENERAL_ERROR_MESSAGE);
        assertEquals("Вы ошиблись в почте или пароле", loginModal.getText(GENERAL_ERROR_MESSAGE));

    }

    @Test
    @DisplayName("Ошибка при пустых полях email и password")
    public void testEmptyEmailAndPasswordShowErrorMessages() {
        loginModal.clickSubmit();

        WaitUtils.waitForVisibility(driver, EMAIL_ERROR_MESSAGE);
        WaitUtils.waitForVisibility(driver, PASSWORD_ERROR_MESSAGE);
        assertAll(
                () -> assertEquals("Введите вашу электронную почту", loginModal.getText(EMAIL_ERROR_MESSAGE)),
                () -> assertEquals("Введите ваш пароль", loginModal.getText(PASSWORD_ERROR_MESSAGE))
        );
    }

    @Test
    @DisplayName("Ошибка при вводе email в неверном формате")
    public void testInvalidEmailFormatShowsErrorMessage() {
        loginModal.enterEmail("test.email");
        loginModal.enterPassword(ConfigReader.get("invalid.password"));
        loginModal.clickSubmit();

        WaitUtils.waitForVisibility(driver, EMAIL_ERROR_MESSAGE);
        assertEquals("Неверный формат электронной почты", loginModal.getText(EMAIL_ERROR_MESSAGE));
    }
}
