package ru.kupibilet.ui.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.components.SearchForm;
import ru.kupibilet.ui.popups.LoginDialog;
import ru.kupibilet.ui.screens.base.BasePage;
import ru.kupibilet.utils.ui.WaitUtils;

public class HomePage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);
    private final By loginButtonLocator = By.cssSelector("[data-testid='open-auth-modal-button']");

    private final SearchForm searchForm;

    public HomePage(WebDriver driver) {
        super(driver);
        log.info("Initializing Home page");
        searchForm = new SearchForm(driver);
    }

    public SearchForm getSearchForm() {
        return searchForm;
    }

    public LoginDialog clickLoginButton() {
        log.info("Clicking the Login button [{}]", loginButtonLocator);
        click(loginButtonLocator);

        WaitUtils.waitForVisibility(driver, LoginDialog.ROOT);

        log.info("Login modal is now visible");
        return new LoginDialog(driver);
    }
}
