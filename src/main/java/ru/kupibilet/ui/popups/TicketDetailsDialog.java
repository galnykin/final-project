package ru.kupibilet.ui.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.components.base.BaseDialogComponent;
import ru.kupibilet.utils.ui.WaitUtils;

public class TicketDetailsDialog extends BaseDialogComponent {

    private static final Logger log = LoggerFactory.getLogger(TicketDetailsDialog.class);

    private final By routeHeader = By.xpath("//div[@class='sc-1jmzv0a-7 iKnwAK']");

    private final By departureCity = By.xpath("""
            (//div[@data-testid='itinerary-segment']//span[@class='styled__StyledTypography-sc-1ym9bng-0 hnoHkM'])[1]
            """);
    private final By arrivalCity = By.xpath("""
            (//div[@data-testid='itinerary-segment']//span[@class='styled__StyledTypography-sc-1ym9bng-0 hnoHkM'])[2]
            """);

    private final By flightNumber = By.cssSelector("");
    private final By departureTime = By.cssSelector("");
    private final By arrivalTime = By.cssSelector("");

    private final By totalPrice = By.cssSelector("[data-testid='ticket-modal-footer'] h2");
    private final By buyButton = By.cssSelector("[data-testid='ticket-price-button']");

    public TicketDetailsDialog(WebDriver driver) {
        super(driver);
    }

    public boolean containsCities(String from, String to) {
        boolean hasFrom = containsDepartureCity(from);
        boolean hasTo = containsArrivalCity(to);

        log.info("Departure match: {}, Arrival match: {}", hasFrom, hasTo);
        return hasFrom && hasTo;
    }

    public boolean containsDepartureCity(String city) {
        return getDepartureCity().contains(city);
    }

    public boolean containsArrivalCity(String city) {
        return getArrivalCity().contains(city);
    }

    public boolean isVisible() {
        return isVisible(ROOT);
    }

    public void close() {
        closeByClickOutside();
    }

    public boolean isClosed() {
        return isClosed(ROOT);
    }

    public String getRouteHeaderText() {
        return getText(routeHeader);
    }

    public String getDepartureCity() {
        return getText(departureCity);
    }

    public String getArrivalCity() {
        return getText(arrivalCity);
    }

    public String getFlightNumber() {
        return getText(flightNumber);
    }

    public String getDepartureTime() {
        return getText(departureTime);
    }

    public String getArrivalTime() {
        return getText(arrivalTime);
    }

    public String getTotalPrice() {
        return getText(totalPrice);
    }

    public boolean isBuyButtonEnabled() {
        return isEnabled(buyButton);
    }

    public void waitUntilVisible() {
        log.debug("Wait for dialog display TicketDetailsDialog");
        WaitUtils.waitUntilVisible(driver, ROOT, 5);
    }

}
