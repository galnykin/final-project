package ru.kupibilet.ui.pages.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.kupibilet.ui.pages.base.BasePage;

public class HomePage extends BasePage {

    private final By loginButtonLocator = By.cssSelector("[data-testid='open-auth-modal-button']");

    public HomePage(WebDriver driver) {
        super(driver);
        log.info("Initializing Home page");
    }

    public LoginModal clickLoginButton() {
        log.info("Clicking the Login button [{}]", loginButtonLocator);
        click(loginButtonLocator);
        log.info("Opening login modal");
        return new LoginModal(driver);
    }
}
