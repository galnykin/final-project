package ui.tests;

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
import ru.kupibilet.testdata.FlightSearchDataFactory;
import ru.kupibilet.ui.screens.HomePage;
import ru.kupibilet.ui.components.SearchForm;
import ui.assertions.FlightAssertions;
import ui.steps.FlightSearchSteps;

@Epic("")
@Feature("")
@Owner("sergey")
@Tag("ui")
public class FlightSearchTest extends BaseTest {

    private SearchForm searchForm;

    @BeforeEach
    public void init() {
        searchForm = new HomePage(driver).getSearchForm();
    }

    @Test
    @Story("User searches for a flight and sees available ticket cards")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Route header should contain departure and arrival cities")
    public void testTicketDetailsDialogHeaderContainsCities() {
        FlightSearchSteps ctx = FlightSearchSteps.with(searchForm)
                .searchTo(FlightSearchDataFactory.randomCapitalCity())
                .openDetails();

        FlightAssertions.assertTicketDetailsDialogHeaderContainsCities(ctx.details(), ctx.query());
    }

    @Test
    @Story("User searches for a flight and sees available ticket cards")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should correctly extract arrival city from ticket details (random destination)")
    public void testArrivalCityFromTicketDetails() {
        FlightSearchSteps ctx = FlightSearchSteps.with(searchForm)
                .searchTo(FlightSearchDataFactory.randomCapitalCity())
                .openDetails();

        FlightAssertions.assertArrivalCityMatches(ctx);
    }

    @Test
    @Story("User searches for a flight and sees available ticket cards")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Should correctly extract departure city from ticket details")
    public void testDepartureCityFromTicketDetails() {
        FlightSearchSteps ctx = FlightSearchSteps.with(searchForm)
                .searchTo(FlightSearchDataFactory.randomCapitalCity())
                .openDetails();

        FlightAssertions.assertDepartureCityMatches(ctx);
    }

    @Test
    @Story("User searches for a flight and sees available ticket cards")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("Should correctly extract arrival city from ticket details (random from/to)")
    public void testArrivalCityFromTicketDetailsWithRandomCities() {
        String from = FlightSearchDataFactory.randomCapitalCity();
        String to = FlightSearchDataFactory.randomCapitalCity();

        FlightSearchSteps ctx = FlightSearchSteps.with(searchForm)
                .searchFromTo(from, to)
                .openDetails();

        FlightAssertions.assertArrivalCityMatches(ctx);
    }
}
