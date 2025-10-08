package ru.kupibilet.auth.testdata;

import com.github.javafaker.Faker;
import ru.kupibilet.auth.dto.FlightSearchQuery;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class FlightSearchDataFactory {

    private static final Faker faker = new Faker();

    public static String randomCity() {
        return faker.address().city();
    }

    public static String randomCapitalCity() {
        return capitalCities.get(new Random().nextInt(capitalCities.size()));
    }

    public static FlightSearchQuery randomFlightQuery() {
        return new FlightSearchQuery(
                randomCity(),
                randomCity(),
                LocalDate.now().plusDays(5)
        );
    }

    private static final List<String> capitalCities = List.of(
            "Москва", "Берлин", "Париж", "Лондон", "Мадрид",
            "Рим", "Прага", "Варшава", "Вена", "Брюссель",
            "Амстердам", "Копенгаген", "Стокгольм", "Хельсинки", "Афины"
    );
}
