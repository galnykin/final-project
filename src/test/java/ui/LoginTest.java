package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ru.kupibilet.ui.pages.HomePage;
import ru.kupibilet.ui.pages.LoginModal;
import ru.kupibilet.ui.utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest extends BaseTest {

    private HomePage homePage;
    private LoginModal loginModal;

    @BeforeEach
    public void init() {
        homePage = new HomePage(driver);
        loginModal = new LoginModal(driver);
    }

    @Test
    public void testLoginFlow() {
        homePage.clickLoginButton();
        loginModal.enterEmail("correct.address@gmail.com");
        loginModal.enterPassword("correctPassword123");
        loginModal.clickSubmit();
    }

    @Test
    public void testInvalidLoginShowsErrorMessage() {
        homePage.clickLoginButton();
        loginModal.enterEmail("myemail@gmail.com");
        loginModal.enterPassword("fljsolcvm4094u");
        loginModal.clickSubmit();

        By errorLocator = By.cssSelector("span.sc-qyqger-0.gUhGbX");
        WaitUtils.waitForVisibility(driver, errorLocator);

        assertEquals("Вы ошиблись в почте или пароле", loginModal.getErrorMessageText());
    }
}
