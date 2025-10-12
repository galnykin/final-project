package ui.assertions;

import ru.kupibilet.search.dto.FlightSearchQuery;
import ru.kupibilet.ui.popups.TicketDetailsDialog;
import ui.steps.FlightSearchSteps;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlightAssertions {

    public static void assertArrivalCityMatches(FlightSearchSteps ctx) {
        String actual = ctx.details().resolveArrivalCity();
        String expected = ctx.query().getArrivalCity();
        assertTrue(actual.contains(expected),
                "Arrival city mismatch: expected [" + expected + "], but was [" + actual + "]");
    }

    public static void assertDepartureCityMatches(FlightSearchSteps ctx) {
        String actual = ctx.details().resolveDepartureCity();
        String expected = ctx.query().getDepartureCity();
        assertTrue(actual.contains(expected),
                "Departure city mismatch: expected [" + expected + "], but was [" + actual + "]");
    }

    public static void assertTicketDetailsDialogHeaderContainsCities(TicketDetailsDialog details, FlightSearchQuery query) {
        String header = details.getRouteHeaderText();
        assertAll(
                () -> assertTrue(header.contains(query.getDepartureCity()), "Missing departure city"),
                () -> assertTrue(header.contains(query.getArrivalCity()), "Missing arrival city")
        );
    }
}
