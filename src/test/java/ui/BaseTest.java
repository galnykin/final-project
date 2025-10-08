package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.drivers.DriverManager;
import ru.kupibilet.ui.utils.EnvironmentConfig;

public abstract class BaseTest {

    protected static final String baseUrl = EnvironmentConfig.BASE_URL;
    protected WebDriver driver;
    protected static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeEach
    public void setUp() {
        log.info("Initializing WebDriver");
        driver = DriverManager.getDriver();
        driver.get(baseUrl);
    }

    @AfterEach
    public void tearDown() {
        log.info("Closing WebDriver session");
        DriverManager.quitDriver();
    }
}
