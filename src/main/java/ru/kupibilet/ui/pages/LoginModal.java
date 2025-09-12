package ru.kupibilet.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginModal {
    private WebDriver driver;

    private By emailField = By.cssSelector("[data-testid='email-input']");
    private By passwordField = By.cssSelector("[data-testid='password-input']");
    private By signInButton = By.cssSelector("[data-testid='sign-in-button']");
    private By registerLink = By.linkText("Регистрация");
    private By forgotPasswordLink = By.linkText("Забыли пароль?");
    private By generalErrorMessage = By.cssSelector("span.sc-qyqger-0.gUhGbX");
    private By emailErrorMessage = By.cssSelector("""
            div:has(input[data-testid="email-input"]) + div.styled__StyledContainer-sc-awcqi9-0.iLlVPd""");
    private By passwordErrorMessage = By.cssSelector("""
            div:has(input[data-testid="password-input"]) + div.styled__StyledContainer-sc-awcqi9-0.iLlVPd""");

    public LoginModal(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSubmit() {
        driver.findElement(signInButton).click();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    public WebElement getErrorMessageElement() {
        return driver.findElement(generalErrorMessage);
    }

    public String getGeneralErrorMessageText() {
        return driver.findElement(generalErrorMessage).getText();
    }

    public String getEmailErrorMessageText() {
        return driver.findElement(emailErrorMessage).getText();
    }

    public String getPasswordErrorMessageText() {
        return driver.findElement(passwordErrorMessage).getText();
    }

    public By getPasswordErrorMessageLocator() {
        return passwordErrorMessage;
    }

    public By getEmailErrorMessageLocator() {
        return emailErrorMessage;
    }

    public By getGeneralErrorMessageLocator() {
        return generalErrorMessage;
    }
}
