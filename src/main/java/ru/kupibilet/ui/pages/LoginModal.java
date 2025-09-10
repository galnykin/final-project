package ru.kupibilet.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginModal {
    private WebDriver driver;

    private By emailField = By.cssSelector("[data-testid='email-input']");
    private By passwordField = By.cssSelector("[data-testid='password-input']");
    private By signInButton = By.cssSelector("[data-testid='sign-in-button']");
    private By registerLink = By.linkText("Регистрация");
    private By forgotPasswordLink = By.linkText("Забыли пароль?");

    public LoginModal(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }

    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }

    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }
}
