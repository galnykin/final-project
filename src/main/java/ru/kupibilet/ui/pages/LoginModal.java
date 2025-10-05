package ru.kupibilet.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static ru.kupibilet.ui.locators.LoginModalLocators.*;

public class LoginModal extends BasePage {

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
