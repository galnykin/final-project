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
    @DisplayName("Успешный вход с корректными данными")
    public void testLoginFlow() {
        loginModal.enterEmail(ConfigReader.get("valid.email"));
        loginModal.enterPassword(ConfigReader.get("valid.password"));
        loginModal.clickSubmit();
    }

    @Test
    @DisplayName("Ошибка при входе с неверными данными")
    public void testInvalidEmailAndPasswordShowsErrorMessage() {
        loginModal.enterEmail(ConfigReader.get("invalid.email"));
        loginModal.enterPassword(ConfigReader.get("invalid.password"));
        loginModal.clickSubmit();

        WaitUtils.waitForVisibility(driver, loginModal.getGeneralErrorMessageLocator());
        assertEquals("Вы ошиблись в почте или пароле", loginModal.getGeneralErrorMessageText());
    }

    @Test
    @DisplayName("Ошибка при пустых полях email и password")
    public void testEmptyEmailAndPasswordShowErrorMessages() {
        loginModal.clickSubmit();

        WaitUtils.waitForVisibility(driver, loginModal.getEmailErrorMessageLocator());
        WaitUtils.waitForVisibility(driver, loginModal.getPasswordErrorMessageLocator());
        assertAll(
                () -> assertEquals("Введите вашу электронную почту", loginModal.getEmailErrorMessageText()),
                () -> assertEquals("Введите ваш пароль", loginModal.getPasswordErrorMessageText())
        );
    }

    @Test
    @DisplayName("Ошибка при вводе email в неверном формате")
    public void testInvalidEmailFormatShowsErrorMessage() {
        loginModal.enterEmail("test.email");
        loginModal.enterPassword(ConfigReader.get("invalid.password"));
        loginModal.clickSubmit();

        WaitUtils.waitForVisibility(driver, loginModal.getEmailErrorMessageLocator());
        assertEquals("Неверный формат электронной почты", loginModal.getEmailErrorMessageText());
    }
}
