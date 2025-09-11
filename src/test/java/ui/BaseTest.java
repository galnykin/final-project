package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.kupibilet.ui.drivers.DriverFactory;
import ru.kupibilet.ui.utils.ConfigReader;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("base.url"));
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
