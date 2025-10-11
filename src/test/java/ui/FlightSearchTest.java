package ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.kupibilet.search.dto.FlightSearchQuery;
import ru.kupibilet.testdata.FlightSearchDataFactory;
import ru.kupibilet.ui.screens.HomePage;
import ru.kupibilet.ui.screens.SearchResultsPage;
import ru.kupibilet.ui.components.SearchFormComponent;
import ru.kupibilet.ui.popups.TicketDetailsDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Flight Search")
@Feature("search-form-container")
@Owner("sergey")
@Tag("ui")
public class FlightSearchTest extends BaseTest {

    private SearchFormComponent searchForm;

    @BeforeEach
    public void init() {
        searchForm = new HomePage(driver).getSearchForm();
    }

    @Test
    @DisplayName("Should use default values when only destination is provided")
    public void testDefaultSearchValues() {
        String currentCity = searchForm.getCurrentFromCity();
        FlightSearchQuery query = FlightSearchQuery.fromTo(currentCity, FlightSearchDataFactory.randomCapitalCity());

        SearchResultsPage results = searchForm.search(query.getTo());

        WebElement form = driver.findElement(By.cssSelector("[data-testid='search-form-container']"));

        String from = form.findElement(By.cssSelector("[data-testid='from-input']")).getText();
        String date = form.findElement(By.cssSelector("[data-testid='departure-date']")).getText();
        String passengerInfo = form.findElement(By.cssSelector("[data-testid='passenger-summary']")).getText();

        assertAll(
                () -> assertFalse(from.isEmpty(), "Default departure city should be set"),
                () -> assertTrue(date.contains("октября"), "Default date should be visible"),
                () -> assertTrue(passengerInfo.contains("1 пас."), "Default passenger count should be 1"),
                () -> assertTrue(passengerInfo.contains("эконом"), "Default class should be economy")
        );
    }


    @Test
    @DisplayName("Should display correct route in ticket header")
    public void testRouteHeaderContainsCities() {
        String currentCity = searchForm.getCurrentFromCity();
        FlightSearchQuery query = FlightSearchQuery.fromTo(currentCity, FlightSearchDataFactory.randomCapitalCity());

        SearchResultsPage results = searchForm.search(query.getTo());
        assertTrue(results.hasTicketCards(), "Expected at least one ticket card");

        TicketDetailsDialog details = results.openFirstTicketDetails();
        assertTrue(details.isVisible(), "Ticket details dialog should be visible");

        String headerText = details.getRouteHeaderText();

        assertAll(
                () -> assertTrue(headerText.contains(query.getFrom()), "Header should contain departure city"),
                () ->
                        assertTrue(headerText.contains(query.getTo()), "Header should contain arrival city")
        );
    }


    @Story("User searches for a flight and sees available ticket cards")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Should correctly extract arrival city from ticket details")
    public void testArrivalCityFromTicketDetails() {
        String currentCity = searchForm.getCurrentFromCity();
        FlightSearchQuery query = FlightSearchQuery.fromTo(currentCity, FlightSearchDataFactory.randomCapitalCity());
        SearchResultsPage results = searchForm.search(query.getTo());

        assertTrue(results.hasTicketCards(), "Expected at least one ticket card");

        WebElement tripItem = driver.findElement(By.cssSelector("[data-testid='serp-ticket-trip-item']"));
        String tripText = tripItem.getText();

        TicketDetailsDialog details = results.openFirstTicketDetails();
        assertTrue(details.isVisible(), "Ticket details dialog should be visible");

        String arrivalCityText;

        if (tripText.contains("Без пересадок")) {
            arrivalCityText = driver.findElement(By.xpath("(//div[@data-testid='itinerary-segment']//span[@class='styled__StyledTypography-sc-1ym9bng-0 hnoHkM'])[2]")).getText();
        } else {
            int transferCount = countTransfers(tripText); // например, по количеству слов "пересадка"
            int index = 2 * (transferCount + 1); // каждая пересадка добавляет 2 позиции
            String xpath = String.format("(//div[@data-testid='itinerary-segment']//span[@class='styled__StyledTypography-sc-1ym9bng-0 hnoHkM'])[%d]", index);
            arrivalCityText = driver.findElement(By.xpath(xpath)).getText();
        }

        assertTrue(arrivalCityText.contains(query.getTo()), "Arrival city should match search destination");
    }

    private int countTransfers(String tripText) {
        Pattern pattern = Pattern.compile("(\\d+) пересад");
        Matcher matcher = pattern.matcher(tripText);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 1; // если текст есть, но без числа — предполагаем одну пересадку
    }


    @Test
    @DisplayName("Should display at least one ticket card after flight search")
    public void testFlightSearchDisplaysResults2() {
        String currentCity = searchForm.getCurrentFromCity();
        FlightSearchQuery query = FlightSearchQuery.fromTo(currentCity, FlightSearchDataFactory.randomCapitalCity());

        SearchResultsPage results = searchForm.search(query.getTo());

//        List<WebElement> ticketCards = driver.findElements(SearchResultsPage.TICKET_ITEM_LOCATOR);
//        assertFalse(ticketCards.isEmpty(), "Expected at least one ticket card");

        assertTrue(results.hasTicketCards(), "Expected at least one ticket card");

//        WebElement firstCard = ticketCards.get(0);
//        firstCard.click(); // или firstCard.findElement(By.cssSelector(".select-button")).click();

// Ждём появления расширенной карточки
//        WaitUtils.waitUntilVisible(driver, BaseDialogComponent.ROOT, 5);

// Получаем текст из расширенной карточки
//        String expandedCardText = driver.findElement(BaseDialogComponent.ROOT).getText();

// Проверяем, что текст содержит нужные данные
//        assertAll(
//                () -> assertTrue(expandedCardText.contains(query.getFrom()), "Expanded card should contain departure city"),
//                () -> assertTrue(expandedCardText.contains(query.getTo()), "Expanded card should contain arrival city")
//        );
        TicketDetailsDialog details = results.openFirstTicketDetails();
        assertAll(
                () -> assertTrue(details.containsDepartureCity(query.getFrom()), "Should contain departure city"),
                () -> assertTrue(details.containsArrivalCity(query.getTo()), "Should contain arrival city")
        );


//        WebElement firstTicketCard = driver.findElements(ticketItemLocator).get(0);

//        String cardText = firstTicketCard.getText();

//        assertTrue(cardText.contains(query.getFrom()), "Ticket card should contain departure city");
//        assertTrue(cardText.contains(query.getTo()), "Ticket card should contain arrival city");
    }

    @Test
    public void test2() {
        String currentCity = searchForm.getCurrentFromCity();
        FlightSearchQuery query = FlightSearchQuery.fromTo(currentCity, FlightSearchDataFactory.randomCapitalCity());
        searchForm.search(query.getFrom(), query.getTo(), query.getDepartureDate());

    }

    @Test
    public void test3() {
        String currentCity = searchForm.getCurrentFromCity();
        FlightSearchQuery query = FlightSearchQuery.fromTo(currentCity, FlightSearchDataFactory.randomCapitalCity());
        searchForm.search(query);

    }

    @Test
    public void test4() {

    }
}
