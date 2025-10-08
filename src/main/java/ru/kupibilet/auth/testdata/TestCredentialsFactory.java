package ru.kupibilet.auth.testdata;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.Random;

/**
 * Utility class for generating test credentials including valid and invalid email addresses and passwords.
 * Designed for use in automated UI and API tests to simulate various input scenarios.
 */

public class TestCredentialsFactory {
    private static final Faker faker = new Faker();
    private static final Random random = new Random();

    /**
     * Generates a valid random email address.
     *
     * @return a syntactically correct email address
     */
    public static String validEmail() {
        return faker.internet().emailAddress();
    }

    /**
     * Generates an email address missing the '@' symbol.
     * Example: {@code userexample.com}
     *
     * @return invalid email string
     */
    public static String emailMissingAtSymbol() {
        return faker.name().username() + faker.internet().domainName();
    }

    /**
     * Generates an email address missing the domain part.
     * Example: {@code user@}
     *
     * @return invalid email string
     */
    public static String emailMissingDomain() {
        return faker.name().username() + "@";
    }

    /**
     * Generates an email address with spaces in the local part.
     * Example: {@code user name@example.com}
     *
     * @return invalid email string
     */
    public static String emailWithSpaces() {
        return faker.name().firstName() + " " + faker.name().lastName() + "@" + faker.internet().domainName();
    }

    /**
     * Generates an empty email string.
     *
     * @return empty string
     */
    public static String emptyEmail() {
        return "";
    }

    /**
     * Generates an email address with an excessively long local part.
     * Example: {@code a...a@example.com} (100+ characters)
     *
     * @return invalid email string
     */
    public static String longEmail() {
        return faker.lorem().characters(100) + "@example.com";
    }

    /**
     * Generates an email address with a space in the local part.
     * Example: {@code user name@example.com}
     *
     * @return an invalid email string with space before '@'
     */
    public static String emailWithSpaceInLocalPart() {
        return faker.name().firstName() + " " + faker.name().lastName() + "@" + faker.internet().domainName();
    }

    /**
     * Generates an email address with a double dot in the domain part.
     * Example: {@code user@example..com}
     *
     * @return an invalid email string with consecutive dots in domain
     */
    public static String emailWithDoubleDotInDomain() {
        return faker.name().username() + "@example..com";
    }

    /**
     * Generates an email address where the domain starts with a hyphen.
     * Example: {@code user@-example.com}
     *
     * @return an invalid email string with hyphen-prefixed domain
     */
    public static String emailWithHyphenStartDomain() {
        return faker.name().username() + "@-example.com";
    }

    /**
     * Generates an email address with a trailing space after the domain.
     * Example: {@code user@example.com␣}
     *
     * @return an invalid email string with trailing whitespace
     */
    public static String emailWithTrailingSpace() {
        return faker.name().username() + "@" + faker.internet().domainName() + " ";
    }

    /**
     * Generates an email address where the domain starts with a dot.
     * Example: {@code user@.example.com}
     *
     * @return an invalid email string with dot-prefixed domain
     */
    public static String emailWithDotBeforeDomain() {
        return faker.name().username() + "@." + faker.internet().domainName();
    }

    /**
     * Generates an invalid email address without a dot in the domain part.
     * Example: {@code user@com}
     *
     * @return invalid email string missing dot in domain
     */
    public static String emailWithNoDotInDomain() {
        return faker.name().username() + "@com";
    }

    /**
     * Generates an email address without any domain or '@' symbol.
     * Example: {@code plainaddress}
     *
     * @return an invalid email string missing domain and separator
     */
    public static String emailPlainAddress() {
        return faker.name().username();
    }

    /**
     * Generates an email address missing the local part before '@'.
     * Example: {@code @example.com}
     *
     * @return an invalid email string missing local part
     */
    public static String emailMissingLocalPart() {
        return "@" + faker.internet().domainName();
    }

    /**
     * Generates an email address with a dot immediately after '@'.
     * Example: {@code user@.com}
     *
     * @return an invalid email string with malformed domain
     */
    public static String emailWithDotAfterAt() {
        return faker.name().username() + "@." + faker.internet().domainSuffix();
    }

    /**
     * Randomly selects one of all predefined invalid email formats.
     * Covers missing parts, malformed domains, spacing issues, and length violations.
     *
     * @return an invalid email string
     */
    public static String randomInvalidEmailFormat() {
        List<String> invalidEmails = List.of(
                emailPlainAddress(),
                emailMissingLocalPart(),
                emailMissingAtSymbol(),
                emailMissingDomain(),
                emailWithDotAfterAt(),
                emailWithDotBeforeDomain(),
                emailWithNoDotInDomain(),
                emailWithDoubleDotInDomain(),
                emailWithHyphenStartDomain(),
                emailWithSpaceInLocalPart(),
                emailWithSpaces(),
                emailWithTrailingSpace(),
                emptyEmail(),
                longEmail()
        );
        return invalidEmails.get(random.nextInt(invalidEmails.size()));
    }

    /**
     * Generates a random password with default security settings.
     * Includes uppercase letters, digits, and special symbols.
     * Length is between 8 and 16 characters.
     *
     * @return a secure password string
     */
    public static String randomPassword() {
        final int minLength = 8;
        final int maxLength = 16;
        final boolean includeUppercase = true;
        final boolean includeDigits = true;
        final boolean includeSymbols = true;

        return faker.internet().password(
                minLength,
                maxLength,
                includeUppercase,
                includeDigits,
                includeSymbols);
    }

    /**
     * Generates a random password based on custom security settings.
     *
     * @param minLength       minimum password length
     * @param maxLength       maximum password length
     * @param upper           whether to include uppercase letters
     * @param digits          whether to include digits
     * @param symbols         whether to include special characters
     * @return a password string matching the specified criteria
     */
    public static String randomPassword(int minLength, int maxLength, boolean upper, boolean digits, boolean symbols) {
        return faker.internet().password(minLength, maxLength, upper, digits, symbols);
    }


    /**
     * Generates a password containing only letters.
     *
     * @return password string with letters only
     */
    public static String passwordWithOnlyLetters() {
        final int length = 8;
        return faker.letterify("?".repeat(length));
    }

    /**
     * Generates an empty password string.
     *
     * @return an empty string
     */
    public static String emptyPassword() {
        return "";
    }

    /**
     * Generates a password shorter than the minimum required length.
     * Example: {@code abc}
     *
     * @return a short password string
     */
    public static String shortPassword() {
        return faker.internet().password(1, 3, true, true, true);
    }

    /**
     * Generates a password containing only lowercase letters.
     * Example: {@code abcdefgh}
     *
     * @return a password string with lowercase letters only
     */
    public static String passwordWithOnlyLowercaseLetters() {
        return faker.letterify("????????");
    }

    /**
     * Generates a password containing only uppercase letters.
     * Example: {@code ABCDEFGH}
     *
     * @return a password string with uppercase letters only
     */
    public static String passwordWithOnlyUppercaseLetters() {
        return faker.letterify("????????").toUpperCase();
    }

    /**
     * Generates a password containing only digits.
     * Example: {@code 12345678}
     *
     * @return a password string with digits only
     */
    public static String passwordWithOnlyDigits() {
        return faker.numerify("########");
    }

    /**
     * Generates a password containing only special characters.
     * Example: {@code !@#$%^&*}
     *
     * @return a password string with symbols only
     */
    public static String passwordWithOnlySymbols() {
        return "!@#$%^&*";
    }

    /**
     * Generates a password without any digits.
     * Example: {@code Abcdefgh!}
     *
     * @return a password string missing digits
     */
    public static String passwordWithoutDigits() {
        return faker.internet().password(8, 12, true, false, true);
    }

    /**
     * Generates a password without any uppercase letters.
     * Example: {@code abc123!@}
     *
     * @return a password string missing uppercase letters
     */
    public static String passwordWithoutUppercase() {
        return faker.internet().password(8, 12, false, true, true);
    }

    /**
     * Generates a password without any special characters.
     * Example: {@code Abc12345}
     *
     * @return a password string missing symbols
     */
    public static String passwordWithoutSymbols() {
        return faker.internet().password(8, 12, true, true, false);
    }

    /**
     * Generates a password containing spaces.
     * Example: {@code abc 123!}
     *
     * @return a password string with whitespace
     */
    public static String passwordWithSpaces() {
        return "abc 123!";
    }

    /**
     * Generates a password containing non-Latin characters (e.g. Cyrillic).
     * Example: {@code пароль123!}
     *
     * @return a password string with unsupported characters
     */
    public static String passwordWithNonLatinCharacters() {
        return "пароль123!";
    }

    /**
     * Generates a password longer than the maximum allowed length.
     * Example: 100+ characters
     *
     * @return a long password string
     */
    public static String longPassword() {
        return faker.internet().password(100, 120, true, true, true);
    }
}


