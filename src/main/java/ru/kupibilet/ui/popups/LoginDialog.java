package ru.kupibilet.ui.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.testdata.TestCredentialsFactory;
import ru.kupibilet.ui.components.base.BaseDialogComponent;
import ru.kupibilet.utils.ui.SensitiveFieldRegistry;
import ru.kupibilet.utils.ui.WaitUtils;
import ru.kupibilet.auth.dto.Credentials;

public class LoginDialog extends BaseDialogComponent {

    private static final Logger log = LoggerFactory.getLogger(LoginDialog.class);

    public static final String WRONG_EMAIL_OR_PASSWORD_MESSAGE = "Вы ошиблись в почте или пароле";
    public static final String EMAIL_REQUIRED_MESSAGE = "Введите вашу электронную почту";
    public static final String PASSWORD_REQUIRED_MESSAGE = "Введите ваш пароль";
    public static final String EMAIL_FORMAT_INVALID_MESSAGE = "Неверный формат электронной почты";

    public static final By EMAIL_ERROR_MESSAGE_LOCATOR = By.cssSelector("""
            div:has(input[data-testid="email-input"]) + div.styled__StyledContainer-sc-awcqi9-0.iLlVPd""");
    private final By emailInput = By.cssSelector("[data-testid='email-input']");
    private final By passwordInput = By.cssSelector("[data-testid='password-input']");
    private final By submitButton = By.cssSelector("[data-testid='sign-in-button']");
    private final By registerLink = By.linkText("Регистрация");
    private final By forgotPasswordLink = By.linkText("Забыли пароль?");
    private final By authErrorMessage = By.cssSelector("span.sc-qyqger-0.gUhGbX");
    private final By passwordErrorMessage = By.cssSelector("""
            div:has(input[data-testid="password-input"]) + div.styled__StyledContainer-sc-awcqi9-0.iLlVPd""");

    public LoginDialog(WebDriver driver) {
        super(driver);
    }

    public LoginDialog enterEmail(String email) {
        log.info("Typing email: '{}'", email);
        type(emailInput, email);
        return this;
    }

    public LoginDialog enterPassword(String password) {
        log.info("Typing password");
        type(passwordInput, password);
        return this;
    }

    public void clickSubmitButton() {
        log.info("Clicking Submit button [{}]", submitButton);
        click(submitButton);
    }

    public void submitCredentials(String email, String password) {
        log.info("Submitting credentials for email='{}'", email);
        fillOutLoginForm(email, password);
        clickSubmitButton();
    }

    public void submitCredentials(Credentials credentials) {
        fillOutLoginForm(credentials.getEmail(), credentials.getPassword());
        clickSubmitButton();
    }

    private void fillOutLoginForm(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }

    public void submitValidUnregisteredCredentials() {
        log.info("Submitting login form with invalid credentials");
        submitCredentials(new Credentials(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.randomPassword()));
    }

    public void submitInvalidEmail(String email) {
        Credentials credentials = new Credentials(email, TestCredentialsFactory.randomPassword());
        log.info("Submitting login form with invalid email: '{}'", SensitiveFieldRegistry.mask(email));
        submitCredentials(credentials);
    }

    public void clickRegisterLink() {
        click(registerLink);
    }

    public void clickForgotPasswordLink() {
        click(forgotPasswordLink);
    }

    public WebElement getErrorMessageElement() {
        return find(authErrorMessage);
    }

    private String getErrorMessage(By locator) {
        log.info("Waiting for error message");
        WaitUtils.waitForVisibility(driver, locator);
        return getText(locator);
    }

    public String getAuthErrorMessageText() {
        String message = getErrorMessage(authErrorMessage);
        log.info("Error message: '{}'", message);
        return message;
    }

    public String getEmailErrorMessageText() {
        try {
            return driver.findElement(EMAIL_ERROR_MESSAGE_LOCATOR).getText();
        } catch (NoSuchElementException e) {
            throw new AssertionError("Элемент ошибки email не найден", e);
        }
    }

    public String getPasswordErrorMessageText() {
        return getErrorMessage(passwordErrorMessage);
    }

    public boolean isClosed() {
        return isClosed(ROOT);
    }

    public boolean isVisible() {
        return isVisible(ROOT);
    }

    public void close() {
        closeByClickOutside();
    }
}
