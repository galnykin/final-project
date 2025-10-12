package ru.kupibilet.config;

/**
 * Enum representing supported browser types.
 */
public enum BrowserType {
    CHROME,
    FIREFOX,
    EDGE;

    /**
     * Parses a string value into a BrowserType enum.
     *
     * @param value browser name as string
     * @return corresponding BrowserType
     * @throws IllegalArgumentException if browser is unsupported
     */
    public static BrowserType fromString(String value) {
        switch (value.toLowerCase()) {
            case "chrome":
                return CHROME;
            case "firefox":
                return FIREFOX;
            case "edge":
                return EDGE;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + value);
        }
    }
}
