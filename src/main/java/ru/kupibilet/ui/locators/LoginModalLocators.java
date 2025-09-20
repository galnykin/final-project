package ru.kupibilet.ui.locators;

import org.openqa.selenium.By;

public class LoginModalLocators {
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
}
