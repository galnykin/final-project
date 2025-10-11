package ru.kupibilet.ui.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kupibilet.ui.popups.TicketDetailsDialog;
import ru.kupibilet.ui.screens.base.BasePage;
import ru.kupibilet.utils.ui.WaitUtils;

import java.util.List;

public class SearchResultsPage extends BasePage {

    public static final By TICKET_ITEM_LOCATOR = By.cssSelector("[data-testid='serp-ticket-item']");
    private TicketDetailsDialog ticketDetailsDialog;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public TicketDetailsDialog getTicketDetailsDialog() {
        if (ticketDetailsDialog == null) {
            ticketDetailsDialog = new TicketDetailsDialog(driver);
        }
        return ticketDetailsDialog;
    }

    public boolean hasTicketCards() {
        return WaitUtils.waitUntilVisible(driver, TICKET_ITEM_LOCATOR, 5);
    }

    public List<WebElement> getTicketCards() {
        return driver.findElements(TICKET_ITEM_LOCATOR);
    }

    public void clickFirstTicketCard() {
        List<WebElement> cards = getTicketCards();
        if (cards.isEmpty()) {
            throw new NoSuchElementException("No ticket cards found on the search results page");
        }
        cards.get(0).click();
    }

    public TicketDetailsDialog openFirstTicketDetails() {
        clickFirstTicketCard();
        getTicketDetailsDialog().waitUntilVisible();
        return getTicketDetailsDialog();
    }
}
