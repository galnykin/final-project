package ru.kupibilet.ui.pages.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kupibilet.ui.pages.base.BasePage;
import ru.kupibilet.ui.utils.ConfigReader;
import ru.kupibilet.ui.utils.WaitUtils;

public class LoginModal extends BasePage {

    public static final String WRONG_EMAIL_OR_PASSWORD_MESSAGE = "Вы ошиблись в почте или пароле";
    public static final String EMAIL_REQUIRED_MESSAGE = "Введите вашу электронную почту";
    public static final String PASSWORD_REQUIRED_MESSAGE = "Введите ваш пароль";
    public static final String EMAIL_FORMAT_INVALID_MESSAGE = "Неверный формат электронной почты";

    private final By emailFieldLocator = By.cssSelector("[data-testid='email-input']");
    private final By passwordFieldLocator = By.cssSelector("[data-testid='password-input']");
    private final By signInButtonLocator = By.cssSelector("[data-testid='sign-in-button']");
    private final By registerLinkLocator = By.linkText("Регистрация");
    private final By forgotPasswordLinkLocator = By.linkText("Забыли пароль?");
    private final By authErrorMessageLocator = By.cssSelector("span.sc-qyqger-0.gUhGbX");
    private final By emailErrorMessageLocator = By.cssSelector("""
            div:has(input[data-testid="email-input"]) + div.styled__StyledContainer-sc-awcqi9-0.iLlVPd""");
    private final By passwordErrorMessageLocator = By.cssSelector("""
            div:has(input[data-testid="password-input"]) + div.styled__StyledContainer-sc-awcqi9-0.iLlVPd""");

    public LoginModal(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        log.info("Typing email: '{}'", email);
        type(emailFieldLocator, email);
    }

    public void enterPassword(String password) {
        log.info("Typing password");
        type(passwordFieldLocator, password);
    }

    public void clickSignInButton() {
        log.info("Clicking Sign In button [{}]", signInButtonLocator);
        click(signInButtonLocator);
    }

    public void submitCredentials(String email, String password) {
        log.info("Submitting credentials for email='{}'", email);
        enterEmail(email);
        enterPassword(password);
        clickSignInButton();
    }

    public void submitInvalidCredentials() {
        log.info("Submitting login form with invalid credentials");
        submitCredentials(ConfigReader.get("invalid.email"), ConfigReader.get("invalid.password"));
    }

    public void submitInvalidEmailFormat() {
        log.info("Submitting login form with invalid email format");
        String invalidEmailFormat = "test.email";
        submitCredentials(invalidEmailFormat, ConfigReader.get("invalid.password"));
    }

    public void clickRegisterLink() {
        click(registerLinkLocator);
    }

    public void clickForgotPasswordLink() {
        click(forgotPasswordLinkLocator);
    }

    public WebElement getErrorMessageElement() {
        return find(authErrorMessageLocator);
    }

    private String getErrorMessage(By locator) {
        log.info("Waiting for auth error message");
        WaitUtils.waitForVisibility(driver, locator);
        return getText(locator);
    }

    public String getAuthErrorMessageText() {
        String message = getErrorMessage(authErrorMessageLocator);
        log.info("Error message: '{}'", message);
        return message;
    }

    public String getEmailErrorMessageText() {
        return getErrorMessage(emailErrorMessageLocator);
    }

    public String getPasswordErrorMessageText() {
        return getErrorMessage(passwordErrorMessageLocator);
    }
}
