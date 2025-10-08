package ru.kupibilet.ui.pages.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kupibilet.auth.testdata.TestCredentialsFactory;
import ru.kupibilet.ui.pages.base.BasePage;
import ru.kupibilet.ui.utils.SensitiveFieldRegistry;
import ru.kupibilet.ui.utils.WaitUtils;
import ru.kupibilet.auth.dto.Credentials;

public class LoginModal extends BasePage {

    public static final String WRONG_EMAIL_OR_PASSWORD_MESSAGE = "Вы ошиблись в почте или пароле";
    public static final String EMAIL_REQUIRED_MESSAGE = "Введите вашу электронную почту";
    public static final String PASSWORD_REQUIRED_MESSAGE = "Введите ваш пароль";
    public static final String EMAIL_FORMAT_INVALID_MESSAGE = "Неверный формат электронной почты";

    private final By emailFieldLocator = By.cssSelector("[data-testid='email-input']");
    private final By passwordFieldLocator = By.cssSelector("[data-testid='password-input']");
    private final By submitButtonLocator = By.cssSelector("[data-testid='sign-in-button']");
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

    public LoginModal enterEmail(String email) {
        log.info("Typing email: '{}'", email);
        type(emailFieldLocator, email);
        return this;
    }

    public LoginModal enterPassword(String password) {
        log.info("Typing password");
        type(passwordFieldLocator, password);
        return this;
    }

    public void clickSubmitButton() {
        log.info("Clicking Submit button [{}]", submitButtonLocator);
        click(submitButtonLocator);
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

    public void submitEmptyEmail() {
        Credentials credentials = new Credentials(
                TestCredentialsFactory.emptyEmail(),
                TestCredentialsFactory.randomPassword()
        );
        log.info("Submitting login form with empty email");
        submitCredentials(credentials);
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
        log.info("Waiting for error message");
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
