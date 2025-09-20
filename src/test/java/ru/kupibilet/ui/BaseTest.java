package ru.kupibilet.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.kupibilet.ui.drivers.DriverManager;
import ru.kupibilet.ui.utils.ConfigReader;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get(ConfigReader.get("base.url"));
    }

    @AfterEach
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
