package ru.kupibilet.auth.testdata;

import ru.kupibilet.auth.dto.Credentials;
import ru.kupibilet.ui.utils.ConfigReader;

import java.util.List;
import java.util.Random;
import java.util.UUID;

public class TestCredentialsFactory {

    public static final int EMAIL_ID_LENGTH = 8;

    public static Credentials validUser() {
        return new Credentials(
                ConfigReader.get("valid.email"),
                ConfigReader.get("valid.password"));
    }

    public static Credentials emptyFields() {
        return new Credentials("", "");
    }

    public static Credentials invalidEmailFormat() {
        return new Credentials(
                generateInvalidEmail(),
                generateValidPassword());
    }

    public static Credentials missingPassword() {
        return new Credentials(generateValidEmail(), "");
    }

    public static Credentials validUnregisteredCredentials() {
        return generateValidUnregisteredUser();
    }

    private static Credentials generateValidUnregisteredUser() {
        return new Credentials(generateValidEmail(), generateValidPassword());
    }

    private static String generateValidEmail() {
        return "user" + UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, EMAIL_ID_LENGTH) + "@example.com";
    }

    private static String generateValidPassword() {
        return "Pass@" + new Random().nextInt(9999);
    }

    private static String generateInvalidEmail() {
        List<String> invalidEmails = List.of(
                "plainaddress",
                "missing.domain@",
                "@missing.local",
                "user@.com",
                "user@com",
                "user @example.com",
                "user@example..com",
                "user@-example.com",
                "user@example.com ",
                "user@.example.com"
        );
        return invalidEmails.get(new Random().nextInt(invalidEmails.size()));
    }
}
