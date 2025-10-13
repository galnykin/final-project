package ru.kupibilet.testdata;

import com.github.javafaker.Faker;
import ru.kupibilet.search.dto.FlightSearchQuery;
import ru.kupibilet.search.dto.TravelClass;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
public class FlightSearchDataFactory {

    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    private static final List<String> capitalCities = List.of(
            "Москва", "Берлин", "Париж", "Лондон", "Мадрид",
            "Рим", "Прага", "Варшава", "Вена", "Брюссель",
            "Амстердам", "Копенгаген", "Стокгольм", "Хельсинки", "Афины"
    );

    public static String randomCity() {
        return faker.address().city();
    }

    public static String randomCapitalCity() {
        return capitalCities.get(random.nextInt(capitalCities.size()));
    }

    public static FlightSearchQuery randomFlightQueryFromCapitals() {
        String from = randomCapitalCity();
        String to;
        do {
            to = randomCapitalCity();
        } while (from.equalsIgnoreCase(to));

        return FlightSearchQuery.fromTo(from, to);
    }

    public static FlightSearchQuery randomRoundTrip() {
        String from = randomCity();
        String to;
        do {
            to = randomCity();
        } while (from.equalsIgnoreCase(to));

        LocalDate departure = LocalDate.now().plusDays(5);
        LocalDate returnDate = departure.plusDays(7);
        int passengers = new Random().nextInt(3) + 1;

        return FlightSearchQuery.roundTrip(from, to, departure, returnDate, passengers, TravelClass.ECONOMY);
    }

}
