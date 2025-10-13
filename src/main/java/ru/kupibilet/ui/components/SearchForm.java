package ru.kupibilet.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.search.dto.FlightSearchQuery;
import ru.kupibilet.ui.screens.SearchResultsPage;
import ru.kupibilet.ui.components.base.BaseComponent;
import ru.kupibilet.utils.ui.WaitUtils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SearchForm extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(SearchForm.class);

    private final By passengerSummary = By.cssSelector("[data-testid='passenger-input-container']");
    private final By fromCity = By.cssSelector("[data-testid='direction-input-from-input']");
    private final By toCity = By.cssSelector("[data-testid='direction-input-to-input']");
    private final By fromCityDropdown = By.id("react-autowhatever-route-direction-input-from");
    private final By toCityDropdown = By.id("react-autowhatever-route-direction-input-to");
    private final By departureDateContainer = By.cssSelector("[data-testid='departure-date-container']");
    private final By calendarDay = By.xpath("//div[@data-testid='search-form-calendar'");
    private final By backDateContainer = By.cssSelector("[data-testid='date-back-container']");
    private final By searchSubmitButton = By.cssSelector("[data-testid='search-ticket-button']");

    private PassengerSummaryDropdown passengerSummaryDropdown;

    public SearchForm(WebDriver driver) {
        super(driver);
    }

    public PassengerSummaryDropdown getPassengerSummaryDropdown() {
        if (passengerSummaryDropdown == null) {
            passengerSummaryDropdown = new PassengerSummaryDropdown(driver);
        }
        return passengerSummaryDropdown;
    }

    public void openPassengerDropdown() {
        click(passengerSummary);
    }

    protected void type(By locator, String text) {
        WebElement element = driver.findElement(locator);

        element.click();
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        element.sendKeys(Keys.DELETE);

        String currentValue = element.getAttribute("value");
        if (currentValue != null && !currentValue.isEmpty()) {
            element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            element.sendKeys(Keys.DELETE);
        }

        element.sendKeys(text);
    }

    private void enterFrom(String cityName) {
        type(fromCity, cityName);
        WaitUtils.waitUntilVisible(driver, fromCityDropdown);
    }

    private void enterTo(String cityName) {
        type(toCity, cityName);
        WaitUtils.waitUntilVisible(driver, toCityDropdown);
    }


    public String getCurrentDepartureCity() {
        return find(fromCity).getAttribute("value");
    }

    public void clickSearch() {
        click(searchSubmitButton);
    }

    public SearchResultsPage search(FlightSearchQuery query) {
        enterFrom(query.getDepartureCity());
        enterTo(query.getArrivalCity());
        selectDepartureDate(query.getDepartureDate());

        openPassengerDropdown();
        getPassengerSummaryDropdown().setAdultCount(query.getPassengerCount());
        for (int age : query.getChildrenAges()) {
            getPassengerSummaryDropdown().addChildPassenger(age);
        }

        clickSearch();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage search(String from, String to, LocalDate departureDate) {
        enterFrom(from);
        enterTo(to);
        selectDepartureDate(departureDate);
        clickSearch();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage search(String to) {
        enterTo(to);
        clickSearch();
        return new SearchResultsPage(driver);
    }

    public SearchResultsPage search(String from, String to) {
        enterTo(to);
        enterFrom(from);
        clickSearch();
        return new SearchResultsPage(driver);
    }

    private void selectDepartureDate(LocalDate date) {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot select a past date: " + date);
        }

        click(departureDateContainer);
        String formatted = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        By dayLocator = calendarDay(formatted);

        WaitUtils.waitUntilVisible(driver, dayLocator);
        click(dayLocator);
    }

    private By calendarDay(String date) {
        return By.cssSelector("[data-day='" + date + "']");
    }
}
