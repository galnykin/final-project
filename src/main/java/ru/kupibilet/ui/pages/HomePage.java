package ru.kupibilet.ui.pages;

import org.openqa.selenium.WebDriver;

import static ru.kupibilet.ui.locators.HomePageLocators.LOGIN_BUTTON;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        click(LOGIN_BUTTON);
    }
}
