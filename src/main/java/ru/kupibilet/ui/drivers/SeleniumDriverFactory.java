package ru.kupibilet.ui.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.config.BrowserType;
import ru.kupibilet.ui.config.Config;
import ru.kupibilet.ui.exceptions.DriverInitializationException;

import java.time.Duration;

/**
 * Factory class for initializing and managing WebDriver instances.
 */
public class SeleniumDriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final Logger log = LoggerFactory.getLogger(SeleniumDriverFactory.class);

    private SeleniumDriverFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns the thread-local WebDriver instance.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver.get() == null) {
            initDriver();
        }
        return driver.get();
    }

    /**
     * Initializes the WebDriver based on the configured browser type.
     */
    private static void initDriver() {
        BrowserType browser = Config.getBrowserType();
        log.info("Initializing WebDriver for browser: {}", browser);
        log.info("Headless mode: {}, Maximized: {}", Config.isHeadless(), Config.isMaximized());

        if (browser == null) {
            throw new IllegalStateException("Browser type is not specified in configuration");
        }

        try {
            switch (browser) {
                case CHROME:
                    driver.set(createChromeDriver());
                    break;
                case FIREFOX:
                    driver.set(createFirefoxDriver());
                    break;
                case EDGE:
                    driver.set(createEdgeDriver());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + browser);
            }

            driver.get().manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(Config.getImplicitWaitTimeout()))
                    .pageLoadTimeout(Duration.ofSeconds(Config.getPageLoadTimeout()));
        } catch (Exception e) {
            throw new DriverInitializationException("Failed to initialize WebDriver: " + browser, e);
        }
    }

    private static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        log.debug("ChromeOptions: {}", options.asMap());
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }
        options.addArguments("--width=" + Config.getBrowserWidth());
        options.addArguments("--height=" + Config.getBrowserHeight());
        log.debug("FirefoxOptions: {}", options.asMap());
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        EdgeOptions options = new EdgeOptions();
        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }
        options.addArguments("--start-maximized");
        log.debug("EdgeOptions: {}", options.asMap());
        return new EdgeDriver(options);
    }

    /**
     * Quits the WebDriver and releases resources.
     */
    public static void quitDriver() {
        if (driver.get() != null) {
            log.info("Quitting WebDriver");
            try {
                driver.get().quit();
            } finally {
                driver.remove();
            }
        }
    }

    /**
     * Resets the WebDriver by quitting and reinitializing it.
     */
    public static void resetDriver() {
        log.warn("Resetting WebDriver");
        quitDriver();
        getDriver();
    }
}
