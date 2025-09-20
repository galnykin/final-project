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
        type(EMAIL_FIELD, email);
    }

    public void enterPassword(String password) {
        type(PASSWORD_FIELD, password);
    }

    public void clickSubmit() {
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
        return getText(GENERAL_ERROR_MESSAGE);
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
