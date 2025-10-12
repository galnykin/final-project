package ru.kupibilet.ui.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kupibilet.ui.components.TicketAirplaneCard;
import ru.kupibilet.ui.popups.TicketDetailsDialog;
import ru.kupibilet.ui.screens.base.BasePage;
import ru.kupibilet.utils.ui.WaitUtils;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage extends BasePage {

    public static final By TICKET_ITEM = By.xpath("//div[@data-testid='serp-ticket-item']");
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
        return WaitUtils.waitUntilVisible(driver, TICKET_ITEM, 15);
    }

    public List<TicketAirplaneCard> getTicketCards() {
        List<WebElement> elements = findAll(TICKET_ITEM);
        return elements.stream()
                .map(el -> new TicketAirplaneCard(driver, el))
                .collect(Collectors.toList());
    }

    public TicketAirplaneCard getFirstTicketCard() {
        return getTicketCards().stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No ticket cards found"));
    }

    public TicketDetailsDialog openFirstTicketDetails() {
        TicketAirplaneCard firstCard = getFirstTicketCard();
        firstCard.click();
        getTicketDetailsDialog().waitUntilVisible();
        return getTicketDetailsDialog();
    }
}
