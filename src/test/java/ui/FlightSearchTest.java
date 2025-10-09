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
import ru.kupibilet.auth.dto.FlightSearchQuery;
import ru.kupibilet.ui.pages.HomePage;
import ru.kupibilet.ui.utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Flight Search")
@Feature("search-form-container")
@Owner("sergey")
@Tag("ui")
public class FlightSearchTest extends BaseTest {

    private HomePage homePage;

    @BeforeEach
    public void init() {
        homePage = new HomePage(driver);
    }

    @Story("User searches for a flight and sees available ticket cards")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Should display at least one ticket card after flight search")
    public void testFlightSearchDisplaysResults() {
        FlightSearchQuery query = FlightSearchQuery.randomFlightQueryFromCapitals();
        homePage.search(query.getTo());

        boolean hasResults = WaitUtils.waitUntilVisible(driver, HomePage.TICKET_ITEM_LOCATOR);

        assertTrue(hasResults, "Expected at least one ticket card to be displayed");

//        WebElement firstTicketCard = driver.findElements(ticketItemLocator).get(0);

//        String cardText = firstTicketCard.getText();

//        assertTrue(cardText.contains(query.getFrom()), "Ticket card should contain departure city");
//        assertTrue(cardText.contains(query.getTo()), "Ticket card should contain arrival city");
    }
}
