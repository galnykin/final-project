package ru.kupibilet.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.components.base.BaseComponent;

/**
 * Represents a single flight segment in the itinerary.
 * A segment contains departure and arrival cities, and may include additional details
 * such as flight number, times, and airline.
 */
public class FlightSegment extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(FlightSegment.class);

    private final WebElement root;

    private static final By cityFrom = By.xpath(
            ".//div[@class='sc-1xik274-0 deLEaY sc-jMsorb ktveUU']" +
                    "/span[@class='styled__StyledTypography-sc-1ym9bng-0 hnoHkM']"
    );
    private static final By cityTo  = By.xpath(
            ".//div[@class='sc-1xik274-0 deLEaY sc-cXawGu eSlFdP']" +
                    "/span[@class='styled__StyledTypography-sc-1ym9bng-0 hnoHkM']"
    );

    public FlightSegment(WebDriver driver, WebElement root) {
        super(driver);
        this.root = root;
    }

    /**
     * Returns the departure city from this segment.
     *
     * @return departure city name
     */
    public String getDepartureCity() {
        return root.findElement(cityFrom).getText();
    }

    /**
     * Returns the arrival city from this segment.
     *
     * @return arrival city name
     */
    public String getArrivalCity() {
        return root.findElement(cityTo).getText();
    }

    /**
     * Verifies that both departure and arrival cities match expected values.
     *
     * @param from expected departure city
     * @param to   expected arrival city
     * @return true if both match, false otherwise
     */
    public boolean containsCities(String from, String to) {
        boolean hasFrom = containsDepartureCity(from);
        boolean hasTo = containsArrivalCity(to);
        log.info("Departure match: {}, Arrival match: {}", hasFrom, hasTo);
        return hasFrom && hasTo;
    }

    /**
     * Checks if the departure city matches the expected value.
     *
     * @param city expected departure city
     * @return true if matches, false otherwise
     */
    public boolean containsDepartureCity(String city) {
        return getDepartureCity().contains(city);
    }

    /**
     * Checks if the arrival city matches the expected value.
     *
     * @param city expected arrival city
     * @return true if matches, false otherwise
     */
    public boolean containsArrivalCity(String city) {
        return getArrivalCity().contains(city);
    }

    // TODO: add getDepartureTime(), getArrivalTime(), getFlightNumber()
}

