package ru.kupibilet.ui.popups;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.components.FlightSegment;
import ru.kupibilet.ui.components.base.BaseDialogComponent;
import ru.kupibilet.utils.ui.WaitUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the ticket details dialog that appears after selecting a flight card.
 * Provides access to route information, pricing, and interaction with the purchase button.
 */
public class TicketDetailsDialog extends BaseDialogComponent {

    private static final Logger log = LoggerFactory.getLogger(TicketDetailsDialog.class);

    private static final By routeHeader = By.xpath("//div[@class='sc-1jmzv0a-7 iKnwAK']");
    private static final By flightSegment = By.xpath("//div[@data-testid='itinerary-segment']");
    private static final By totalPrice = By.cssSelector("[data-testid='ticket-modal-footer'] h2");
    private static final By buyButton = By.cssSelector("[data-testid='ticket-price-button']");

    public TicketDetailsDialog(WebDriver driver) {
        super(driver);
    }

    /**
     * Waits until the dialog becomes visible.
     */
    public void waitUntilVisible() {
        log.debug("Waiting for TicketDetailsDialog to become visible");
        WaitUtils.waitUntilVisible(driver, ROOT, 5);
    }

    /**
     * Checks whether the dialog is currently visible.
     *
     * @return true if visible, false otherwise
     */
    public boolean isVisible() {
        return isVisible(ROOT);
    }

    /**
     * Closes the dialog by clicking outside its bounds.
     */
    public void close() {
        closeByClickOutside();
    }

    /**
     * Checks whether the dialog has been closed.
     *
     * @return true if closed, false otherwise
     */
    public boolean isClosed() {
        return isClosed(ROOT);
    }

    /**
     * Returns the full route header text.
     *
     * @return route header string
     */
    public String getRouteHeaderText() {
        return getText(routeHeader);
    }

    /**
     * Returns the total price displayed in the dialog.
     *
     * @return price string
     */
    public String getTotalPrice() {
        return getText(totalPrice);
    }

    /**
     * Checks whether the "Buy" button is enabled.
     *
     * @return true if enabled, false otherwise
     */
    public boolean isBuyButtonEnabled() {
        return isEnabled(buyButton);
    }

    /**
     * Returns a list of all flight segments in the itinerary.
     *
     * @return list of FlightSegment components
     */
    public List<FlightSegment> getSegments() {
        List<WebElement> segmentElements = findAll(flightSegment);
        List<FlightSegment> segments = new ArrayList<>();

        for (WebElement el : segmentElements) {
            segments.add(new FlightSegment(driver, el));
        }

        return segments;
    }

    /**
     * Resolves the final arrival city based on the last segment in the itinerary.
     *
     * @return arrival city string
     */
    public String resolveArrivalCity() {
        List<FlightSegment> segments = getSegments();
        if (segments.size() == 1) {
            return segments.get(0).getArrivalCity();
        }
        return segments.get(segments.size() - 1).getArrivalCity();
    }

    public String resolveDepartureCity() {
        List<FlightSegment> segments = getSegments();
        return segments.get(0).getDepartureCity();
    }
}
