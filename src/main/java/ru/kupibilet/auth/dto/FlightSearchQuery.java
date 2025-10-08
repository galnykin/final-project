package ru.kupibilet.auth.dto;

import java.time.LocalDate;

import static ru.kupibilet.auth.testdata.FlightSearchDataFactory.randomCapitalCity;

public class FlightSearchQuery {
    private final String from;
    private final String to;
    private final LocalDate departureDate;

    public FlightSearchQuery(String from, String to, LocalDate departureDate) {
        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
    }

    public static FlightSearchQuery randomFlightQueryFromCapitals() {
        String from = randomCapitalCity();
        String to;
        do {
            to = randomCapitalCity();
        } while (from.equals(to));

        return new FlightSearchQuery(from, to, LocalDate.now().plusDays(5));
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }
}
