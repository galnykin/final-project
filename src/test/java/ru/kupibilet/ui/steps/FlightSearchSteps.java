package ru.kupibilet.ui.steps;

import ru.kupibilet.search.dto.FlightSearchQuery;
import ru.kupibilet.ui.components.SearchForm;
import ru.kupibilet.ui.popups.TicketDetailsDialog;
import ru.kupibilet.ui.screens.SearchResultsPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Fluent interface for performing flight search steps in UI tests.
 * Encapsulates the search form, query parameters, search results, and ticket details.
 */
public class FlightSearchSteps {

    /**
     * Reference to the search form page object
     */
    private final SearchForm searchForm;

    /**
     * Search query containing origin and destination cities
     */
    private FlightSearchQuery query;

    /**
     * Page object representing the search results
     */
    private SearchResultsPage results;

    /**
     * Dialog component showing ticket details
     */
    private TicketDetailsDialog details;

    /**
     * Private constructor to enforce fluent usage via {@code with()}.
     *
     * @param searchForm the search form page object
     */
    private FlightSearchSteps(SearchForm searchForm) {
        this.searchForm = searchForm;
    }

    /**
     * Entry point for fluent flight search steps.
     *
     * @param searchForm the search form page object
     * @return a new instance of {@code FlightSearchSteps}
     */
    public static FlightSearchSteps with(SearchForm searchForm) {
        return new FlightSearchSteps(searchForm);
    }

    /**
     * Performs a flight search using the specified destination city.
     * Initializes the {@code query} and {@code results} fields.
     *
     * @param destination the destination city
     * @return the current {@code FlightSearchSteps} instance for chaining
     */
    public FlightSearchSteps searchTo(String destination) {
        this.query = FlightSearchQuery.fromTo(
                searchForm.getCurrentDepartureCity(),
                destination
        );
        this.results = searchForm.search(query.getArrivalCity());
        assertTrue(results.hasTicketCards(), "Expected at least one ticket card");
        return this;
    }

    /**
     * Performs a flight search using the provided query and opens the details dialog
     * for the first available ticket.
     *
     * @param query the flight search query containing departure and arrival cities, dates, and other parameters
     * @return the ticket details dialog for the first search result
     * @throws AssertionError if no ticket cards are found in the search results
     */
    public TicketDetailsDialog searchWithQueryAndOpenDetails(FlightSearchQuery query) {
        this.query = query;
        this.results = searchForm.search(query.getArrivalCity());
        assertTrue(results.hasTicketCards(), "Expected at least one ticket card");
        this.details = results.openFirstTicketDetails();
        return details;
    }

    /**
     * Performs a flight search using the specified departure and arrival cities.
     *
     * @param from the departure city
     * @param to   the arrival city
     * @return the current instance of FlightSearchSteps for method chaining
     * @throws AssertionError if no ticket cards are found in the search results
     */
    public FlightSearchSteps searchFromTo(String from, String to) {
        this.query = FlightSearchQuery.fromTo(from, to);
        this.results = searchForm.search(query.getDepartureCity(), query.getArrivalCity());
        assertTrue(results.hasTicketCards(), "Expected at least one ticket card");
        return this;
    }

    /**
     * Performs a flight search using the provided FlightSearchQuery object.
     *
     * @param query the flight search query containing all necessary parameters
     * @return the current instance of FlightSearchSteps for method chaining
     * @throws AssertionError if no ticket cards are found in the search results
     */
    public FlightSearchSteps searchWithQuery(FlightSearchQuery query) {
        this.query = query;
        this.results = searchForm.search(query.getArrivalCity());
        assertTrue(results.hasTicketCards(), "Expected at least one ticket card");
        return this;
    }

    /**
     * Opens the details dialog for the first ticket in the search results.
     * Initializes the {@code details} field.
     *
     * @return the current {@code FlightSearchSteps} instance for chaining
     */
    public FlightSearchSteps openDetails() {
        this.details = results.openFirstTicketDetails();
        assertTrue(details.isVisible(), "Ticket details dialog should be visible");
        return this;
    }

    /**
     * Returns the search query used in the flight search.
     *
     * @return the {@code FlightSearchQuery} instance
     */
    public FlightSearchQuery query() {
        return query;
    }

    /**
     * Returns the search results page object.
     *
     * @return the {@code SearchResultsPage} instance
     */
    public SearchResultsPage results() {
        return results;
    }

    /**
     * Returns the ticket details dialog component.
     *
     * @return the {@code TicketDetailsDialog} instance
     */
    public TicketDetailsDialog details() {
        return details;
    }
}