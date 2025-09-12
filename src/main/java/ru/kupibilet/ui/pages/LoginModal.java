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
    private By errorMessage = By.cssSelector("span.sc-qyqger-0.gUhGbX");


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
        return driver.findElement(errorMessage);
    }

    public String getErrorMessageText() {
        return driver.findElement(errorMessage).getText();
    }
}
