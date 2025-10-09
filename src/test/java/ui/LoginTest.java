package ui;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.kupibilet.auth.testdata.TestCredentialsFactory;
import ru.kupibilet.ui.pages.HomePage;
import ru.kupibilet.ui.pages.popups.LoginModal;
import ru.kupibilet.ui.utils.WaitUtils;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Authorization")
@Feature("Login Modal Window")
@Owner("sergey")
@Tag("ui")
public class LoginTest extends BaseTest {

    private LoginModal loginModal;

    @BeforeEach
    public void init() {
        loginModal = new HomePage(driver).clickLoginButton();
    }

    @Story("Negative scenario: login attempt with valid but unregistered credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    @DisplayName("Error when logging in with valid but unregistered credentials")
    public void testUnregisteredUserLoginShowsErrorMessage() {
        loginModal.submitValidUnregisteredCredentials();
        assertEquals(LoginModal.WRONG_EMAIL_OR_PASSWORD_MESSAGE, loginModal.getAuthErrorMessageText());
    }

    @Story("Negative scenario: email is plain address")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email is plain address without '@'")
    public void testPlainAddressEmailShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailPlainAddress());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email missing local part")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email is missing local part")
    public void testEmailMissingLocalPartShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailMissingLocalPart());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email missing '@' symbol")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email is missing '@' symbol")
    public void testEmailMissingAtSymbolShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailMissingAtSymbol());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email missing domain")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email is missing domain")
    public void testEmailMissingDomainShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailMissingDomain());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email with dot after '@'")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email has dot immediately after '@'")
    public void testEmailWithDotAfterAtShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailWithDotAfterAt());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email with dot before domain")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email has dot before domain")
    public void testEmailWithDotBeforeDomainShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailWithDotBeforeDomain());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email with no dot in domain")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email domain has no dot")
    public void testEmailWithNoDotInDomainShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailWithNoDotInDomain());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email with double dot in domain")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email has double dot in domain")
    public void testEmailWithDoubleDotInDomainShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailWithDoubleDotInDomain());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email with hyphen at start of domain")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email domain starts with hyphen")
    public void testEmailWithHyphenStartDomainShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailWithHyphenStartDomain());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email with space in local part")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email has space in local part")
    public void testEmailWithSpaceInLocalPartShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailWithSpaceInLocalPart());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email with space anywhere")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email contains space")
    public void testEmailWithSpacesShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailWithSpaces());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email with trailing space")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email has trailing space")
    public void testEmailWithTrailingSpaceShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emailWithTrailingSpace());
        assertEquals(LoginModal.EMAIL_FORMAT_INVALID_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email is empty")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email is empty")
    public void testEmptyEmailShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.emptyEmail());
        assertEquals(LoginModal.EMAIL_REQUIRED_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: email is too long")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @Disabled
    @DisplayName("Error when email is too long")
    public void testLongEmailShowsErrorMessage() {
        loginModal.submitInvalidEmail(TestCredentialsFactory.longEmail());
        assertTrue(
                WaitUtils.isElementVisible(driver, LoginModal.EMAIL_ERROR_MESSAGE_LOCATOR),
                "An email error was expected, but the element did not appear on the page."
        );
        assertEquals(LoginModal.EMAIL_REQUIRED_MESSAGE, loginModal.getEmailErrorMessageText());
    }

    @Story("Negative scenario: empty fields")
    @Severity(SeverityLevel.NORMAL)
    @Test
    @DisplayName("Error when email and password fields are empty")
    public void testEmptyEmailAndPasswordShowErrorMessages() {
        loginModal.clickSubmitButton();
        assertAll(
                () -> assertEquals(LoginModal.EMAIL_REQUIRED_MESSAGE, loginModal.getEmailErrorMessageText()),
                () -> assertEquals(LoginModal.PASSWORD_REQUIRED_MESSAGE, loginModal.getPasswordErrorMessageText())
        );
    }
}
