package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.drivers.DriverManager;
import ru.kupibilet.ui.utils.ConfigReader;

public abstract class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        logger.info("Initializing WebDriver");
        driver = DriverManager.getDriver();

        String baseUrl = ConfigReader.get("base.url");
        logger.info("Navigating to base URL: {}", baseUrl);
        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        logger.info("Closing WebDriver session");
        DriverManager.quitDriver();
    }
}
