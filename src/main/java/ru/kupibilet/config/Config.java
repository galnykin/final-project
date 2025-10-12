package ru.kupibilet.config;

import java.time.Duration;

/**
 * Central configuration class for framework settings.
 */
public class Config {

    /**
     * Returns the default timeout for wait operations.
     *
     * @return Duration of default timeout
     */
    public static Duration getDefaultTimeout() {
        return Duration.ofSeconds(10);
    }

    /**
     * Returns the extended timeout for slow environments or heavy pages.
     *
     * @return Duration of extended timeout
     */
    public static Duration getExtendedTimeout() {
        return Duration.ofSeconds(20);
    }
}
