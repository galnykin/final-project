package ru.kupibilet.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kupibilet.ui.pages.base.BasePage;
import ru.kupibilet.ui.pages.components.HeaderComponent;
import ru.kupibilet.ui.pages.popups.LoginModal;
import ru.kupibilet.ui.utils.WaitUtils;

import java.time.LocalDate;

public class HomePage extends BasePage {

    public static final By TICKET_ITEM_LOCATOR = By.cssSelector("[data-testid='serp-ticket-item']");

    private final By loginButtonLocator = By.cssSelector("[data-testid='open-auth-modal-button']");
    private final By searchTicketButtonLocator = By.cssSelector("[data-testid='search-ticket-button']");
    private final By directionFromInputLocator = By.cssSelector("[data-testid='direction-input-from-input']");
    private final By directionToInputLocator = By.cssSelector("[data-testid='direction-input-to-input']");
    private final By departureDateInputInputLocator = By.cssSelector("[data-testid='departure-date']");
    private final By autocompleteDropdownLocator = By.id("react-autowhatever-route-direction-input-to");

    private final HeaderComponent header;

    public HomePage(WebDriver driver) {
        super(driver);
        log.info("Initializing Home page");
        header = new HeaderComponent(driver);
    }

    public HeaderComponent getHeader() {
        return header;
    }

    public LoginModal clickLoginButton() {
        log.info("Clicking the Login button [{}]", loginButtonLocator);
        click(loginButtonLocator);

        WaitUtils.waitForVisibility(driver, LoginModal.MODAL_LOCATOR);

        log.info("Login modal is now visible");
        return new LoginModal(driver);
    }

    public void search(String from, String to, LocalDate departureDate) {
        enterCity(directionFromInputLocator, from);
        enterCity(directionToInputLocator, to);
        enterDepartureDate(departureDate);
        driver.findElement(searchTicketButtonLocator).click();
    }

    public void search(String from, String to) {
        enterCity(directionFromInputLocator, from);
        enterCity(directionToInputLocator, to);
        driver.findElement(searchTicketButtonLocator).click();
    }

    public void search(String to) {
        enterCity(directionToInputLocator, to);
        driver.findElement(searchTicketButtonLocator).click();
    }

    private void enterCity(By locator, String cityName) {
        WebElement cityField = driver.findElement(locator);
        cityField.clear();
        cityField.sendKeys(cityName);

        WaitUtils.waitUntilVisible(driver, autocompleteDropdownLocator);
    }

    private void enterDepartureDate(LocalDate date) {
        WebElement dateField = driver.findElement(departureDateInputInputLocator);
        dateField.clear();
        dateField.sendKeys(date.toString());
    }
}
