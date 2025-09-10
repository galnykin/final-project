package ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.kupibilet.ui.drivers.DriverFactory;
import ru.kupibilet.ui.pages.HomePage;
import ru.kupibilet.ui.pages.LoginModal;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private HomePage homePage;
    private LoginModal loginModal;

    @BeforeEach
    public void setUp() {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get("https://www.kupibilet.ru/");
        homePage = new HomePage(driver);
        loginModal = new LoginModal(driver);
    }

    @Test
    public void testLoginFlow() {
        homePage.clickLoginButton();
        loginModal.enterEmail("myemail@gmail.com");
        loginModal.enterPassword("fljsolcvm4094u");
        loginModal.clickSignIn();
    }

    @Test
    public void testInvalidLoginShowsErrorMessage() {
        homePage.clickLoginButton();
        loginModal.enterEmail("myemail@gmail.com");
        loginModal.enterPassword("fljsolcvm4094u");
        loginModal.clickSignIn();

        By errorLocator = By.cssSelector("span.sc-qyqger-0.gUhGbX");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(errorLocator));

        assertEquals("Вы ошиблись в почте или пароле", loginModal.getErrorMessageText());
    }

    @AfterEach
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
