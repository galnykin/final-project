package ru.kupibilet.ui.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class DriverManager {
    private static WebDriver driver;

    private DriverManager() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private static void initDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");

        driver = new ChromeDriver(options);
        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(5))
                .pageLoadTimeout(Duration.ofSeconds(10));
    }

    public static void quitDriver() {
        if (driver != null) {
            try {
                driver.quit();
            } finally {
                driver = null;
            }
        }
    }

    public static void resetDriver() {
        quitDriver();
        getDriver();
    }
}
