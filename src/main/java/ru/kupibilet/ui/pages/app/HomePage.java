package ru.kupibilet.ui.pages.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.kupibilet.ui.pages.base.BasePage;

public class HomePage extends BasePage {

    public static final By LOGIN_BUTTON = By.cssSelector("[data-testid='open-auth-modal-button']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        log.info("Clicking the Login button [{}]", LOGIN_BUTTON);
        click(LOGIN_BUTTON);
    }
}
