package ru.kupibilet.search.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FlightSearchQuery {

    private static final int DEFAULT_PASSENGER_COUNT = 1;
    private static final TravelClass DEFAULT_TRAVEL_CLASS = TravelClass.ECONOMY;
    private static final int DEFAULT_DEPARTURE_OFFSET_DAYS = 3;

    private final String from;
    private final String to;
    private final LocalDate departureDate;
    private final int passengerCount;
    private final TravelClass travelClass;
    private final LocalDate returnDate;

    public FlightSearchQuery(
            String from,
            String to,
            LocalDate departureDate,
            LocalDate returnDate,
            int passengerCount,
            TravelClass travelClass) {

        if (from == null || to == null || departureDate == null || passengerCount < 1 || travelClass == null) {
            throw new IllegalArgumentException("Invalid flight search parameters");
        }

        this.from = from;
        this.to = to;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.passengerCount = passengerCount;
        this.travelClass = travelClass;
    }

    public FlightSearchQuery(String from, String to) {
        this(
                from,
                to,
                LocalDate.now().plusDays(DEFAULT_DEPARTURE_OFFSET_DAYS),
                null,
                DEFAULT_PASSENGER_COUNT,
                DEFAULT_TRAVEL_CLASS
        );
    }

    public static FlightSearchQuery fromTo(String from, String to) {
        return new FlightSearchQuery(from, to);
    }

    public static FlightSearchQuery oneWay(String from,
                                           String to,
                                           LocalDate departureDate,
                                           int passengerCount,
                                           TravelClass travelClass) {
        return new FlightSearchQuery(from, to, departureDate, null, passengerCount, travelClass);
    }

    public static FlightSearchQuery roundTrip(String departureCity,
                                              String arrivalCity,
                                              LocalDate departureDate,
                                              LocalDate returnDate,
                                              int passengerCount,
                                              TravelClass travelClass) {
        return new FlightSearchQuery(departureCity, arrivalCity, departureDate, returnDate, passengerCount, travelClass);
    }

    public String getDepartureCity() {
        return from;
    }

    public String getArrivalCity() {
        return to;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public TravelClass getTravelClass() {
        return travelClass;

    }

    //TODO
    public List<Integer> getChildrenAges() {
        return new ArrayList<>();
    }
}
