package ru.kupibilet.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.components.base.BaseComponent;
import ru.kupibilet.ui.interfaces.Clickable;

public class TicketAirplaneCard extends BaseComponent implements Clickable {

    private final WebElement root;

    private static final By ticketTotalSum = By.cssSelector("[data-testid='serp-ticket-total-sum']");
    private static final By ticketTripItem = By.cssSelector("[data-testid='serp-ticket-trip-item']");
    private static final By ticketDepartureTime = By.cssSelector("[data-testid='serp-ticket-departure-time']");

    private static final Logger log = LoggerFactory.getLogger(TicketAirplaneCard.class);

    public TicketAirplaneCard(WebDriver driver, WebElement root) {
        super(driver);
        this.root = root;
    }

    public String getTripText() {
        return root.findElement(ticketTripItem).getText();
    }

    public void click() {
        root.click();
    }

    public boolean isDisplayed() {
        return root.isDisplayed();
    }

    public String getPrice() {
        return root.findElement(ticketTotalSum).getText();
    }

    public String getDepartureTime() {
        return root.findElement(ticketDepartureTime).getText();
    }
}

