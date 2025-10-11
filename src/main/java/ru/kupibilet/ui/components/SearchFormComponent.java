package ru.kupibilet.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.search.dto.FlightSearchQuery;
import ru.kupibilet.ui.screens.SearchResultsPage;
import ru.kupibilet.ui.components.base.BaseComponent;
import ru.kupibilet.utils.ui.WaitUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SearchFormComponent extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(SearchFormComponent.class);

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

    public SearchFormComponent(WebDriver driver) {
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

    private void enterFrom(String cityName) {
        type(fromCity, cityName);
        WaitUtils.waitUntilVisible(driver, fromCityDropdown);
    }

    private void enterTo(String cityName) {
        type(toCity, cityName);
        WaitUtils.waitUntilVisible(driver, toCityDropdown);
    }

    public String getCurrentFromCity() {
        return find(fromCity).getAttribute("value");
    }

    public void clickSearch() {
        click(searchSubmitButton);
    }

    public SearchResultsPage search(FlightSearchQuery query) {
        enterFrom(query.getFrom());
        enterTo(query.getTo());
        selectDepartureDate(query.getDepartureDate());

        openPassengerDropdown();
        getPassengerSummaryDropdown().setAdultCount(query.getPassengerCount());
//        for (int age : query.getChildrenAges()) {
//            getPassengerSummaryDropdown().addChildPassenger(age);
//        }

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
        enterFrom(from);
        enterTo(to);
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
