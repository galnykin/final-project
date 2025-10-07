package ru.kupibilet.ui.pages.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kupibilet.ui.pages.base.BasePage;
import ru.kupibilet.ui.utils.WaitUtils;

public class LoginModal extends BasePage {

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

    public void clickSubmit() {
        if (log.isInfoEnabled()) {
            log.info("Clicking Sign In button [{}]", signInButtonLocator);
        }
        click(signInButtonLocator);
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

    public String getAuthErrorMessageText() {
        String message = getText(authErrorMessageLocator);
        log.info("Waiting for auth error message");
        WaitUtils.waitForVisibility(driver, authErrorMessageLocator);
        log.info("Auth error message: '{}'", message);
        return message;
    }

    public String getEmailErrorMessageText() {
        By message = emailErrorMessageLocator;
        WaitUtils.waitForVisibility(driver, message);
        return getText(message);
    }

    public String getPasswordErrorMessageText() {
        By message = passwordErrorMessageLocator;
        WaitUtils.waitForVisibility(driver, message);
        return getText(message);
    }
}
