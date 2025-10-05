package ru.kupibilet.ui.utils;

import org.openqa.selenium.By;

import java.util.Set;

public class SensitiveFieldRegistry {

    private static final Set<String> SENSITIVE_KEYWORDS = Set.of(
            "password",
            "token",
            "secret",
            "key",
            "auth",
            "credential"
    );

    /**
     * Checks whether the given locator is considered sensitive.
     * This prevents logging actual values typed into such fields.
     *
     * @param locator Selenium locator
     * @return true if the locator is sensitive, false otherwise
     */
    public static boolean isSensitive(By locator) {
        if (locator == null) return false;
        String locatorString = locator.toString().toLowerCase();
        return SENSITIVE_KEYWORDS.stream().anyMatch(locatorString::contains);
    }

    /**
     * Masks a sensitive value to prevent exposure in logs.
     * Example: "myPassword123" → "my****23"
     *
     * @param value the original string
     * @return masked version or "[REDACTED]" if null/blank
     */
    public static String mask(String value) {
        if (value == null || value.isBlank()) return "[REDACTED]";
        if (value.length() <= 4) return "****";
        return value.substring(0, 2) + "****" + value.substring(value.length() - 2);
    }
}
