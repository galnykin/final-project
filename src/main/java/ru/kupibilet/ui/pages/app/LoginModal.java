package ru.kupibilet.ui.pages.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kupibilet.ui.pages.base.BasePage;

public class LoginModal extends BasePage {

    public static final By EMAIL_FIELD = By.cssSelector("[data-testid='email-input']");
    public static final By PASSWORD_FIELD = By.cssSelector("[data-testid='password-input']");
    public static final By SIGN_IN_BUTTON = By.cssSelector("[data-testid='sign-in-button']");
    public static final By REGISTER_LINK = By.linkText("Регистрация");
    public static final By FORGOT_PASSWORD_LINK = By.linkText("Забыли пароль?");
    public static final By GENERAL_ERROR_MESSAGE = By.cssSelector("span.sc-qyqger-0.gUhGbX");
    public static final By EMAIL_ERROR_MESSAGE = By.cssSelector("""
            div:has(input[data-testid="email-input"]) + div.styled__StyledContainer-sc-awcqi9-0.iLlVPd""");
    public static final By PASSWORD_ERROR_MESSAGE = By.cssSelector("""
            div:has(input[data-testid="password-input"]) + div.styled__StyledContainer-sc-awcqi9-0.iLlVPd""");

    public LoginModal(WebDriver driver) {
        super(driver);
    }

    public void enterEmail(String email) {
        log.info("Typing email: '{}'", email);
        type(EMAIL_FIELD, email);
    }

    public void enterPassword(String password) {
        log.info("Typing password");
        type(PASSWORD_FIELD, password);
    }

    public void clickSubmit() {
        if (log.isInfoEnabled()) {
            log.info("Clicking Sign In button [{}]", SIGN_IN_BUTTON);
        }
        click(SIGN_IN_BUTTON);
    }

    public void clickRegisterLink() {
        click(REGISTER_LINK);
    }

    public void clickForgotPasswordLink() {
        click(FORGOT_PASSWORD_LINK);
    }

    public WebElement getErrorMessageElement() {
        return find(GENERAL_ERROR_MESSAGE);
    }

    public String getGeneralErrorMessageText() {
        String message = getText(GENERAL_ERROR_MESSAGE);
        log.info("General error message: '{}'", message);
        return message;
    }

    public String getEmailErrorMessageText() {
        return getText(EMAIL_ERROR_MESSAGE);
    }

    public String getPasswordErrorMessageText() {
        return getText(PASSWORD_ERROR_MESSAGE);
    }

    public By getPasswordErrorMessageLocator() {
        return PASSWORD_ERROR_MESSAGE;
    }

    public By getEmailErrorMessageLocator() {
        return EMAIL_ERROR_MESSAGE;
    }

    public By getGeneralErrorMessageLocator() {
        return GENERAL_ERROR_MESSAGE;
    }
}
