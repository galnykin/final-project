package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import ru.kupibilet.ui.drivers.DriverFactory;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.kupibilet.ru/");
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
