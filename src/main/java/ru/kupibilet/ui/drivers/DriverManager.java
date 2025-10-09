package ru.kupibilet.ui.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.kupibilet.ui.config.BrowserType;
import ru.kupibilet.ui.config.Config;

import java.time.Duration;

/**
 * Factory class for initializing and managing WebDriver instances.
 */
public class DriverManager {

    private static WebDriver driver;

    private DriverManager() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Returns the singleton WebDriver instance.
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    /**
     * Initializes the WebDriver based on the configured browser type.
     */
    private static void initDriver() {
        BrowserType browser = Config.getBrowserType();

        switch (browser) {
            case CHROME:
                driver = createChromeDriver();
                break;
            case FIREFOX:
                driver = createFirefoxDriver();
                break;
            case EDGE:
                driver = createEdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type: " + browser);
        }

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5))
                .pageLoadTimeout(Duration.ofSeconds(10));
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        return new ChromeDriver(options);
    }

    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--width=1920");
        options.addArguments("--height=1080");
        return new FirefoxDriver(options);
    }

    private static WebDriver createEdgeDriver() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        return new EdgeDriver(options);
    }

    /**
     * Quits the WebDriver and releases resources.
     */
    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }

    /**
     * Resets the WebDriver by quitting and reinitializing it.
     */
    public static void resetDriver() {
        quitDriver();
        getDriver();
    }
}
