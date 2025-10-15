package ru.kupibilet.api.tests;

import ru.kupibilet.api.assertions.LoginSoftAssert;
import ru.kupibilet.api.base.BaseApiTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.api.clients.AuthClient;
import ru.kupibilet.api.models.LoginRequest;
import ru.kupibilet.testdata.TestCredentialsFactory;

@Epic("Authorization")
@Feature("API Login")
@Tag("api")
@Owner("sergey")
public class NegativeLoginApiTest extends BaseApiTest {

    private static final Logger log = LoggerFactory.getLogger(NegativeLoginApiTest.class);
    private AuthClient authClient;

    @BeforeEach
    public void setUp() {
        authClient = new AuthClient();
    }

    @Test
    @Tag("brokenApi")
    @Story("Login with valid email and random password (unregistered user)")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with unregistered credentials — current behavior (known issue)")
    void testLoginWithUnregisteredCredentials_currentBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithUnregisteredCredentials_currentBehavior] Response:\n{}",
                response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchMalformedLoginServerError()
                .verify();
    }

    @Test
    @Disabled("API behavior not yet aligned with expected contract")
    @Tag("disabledUntilFix")
    @Story("Login with valid email and random password (unregistered user)")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with unregistered credentials — expected behavior")
    void testLoginWithUnregisteredCredentials_expectedBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithUnregisteredCredentials_expectedBehavior] Response:\n{}",
                response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchUnregisteredUserError()
                .verify();
    }

    @Test
    @Tag("brokenApi")
    @Story("Login with malformed email (missing '@')")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with email missing '@' symbol — current behavior (known issue)")
    void testLoginWithEmailMissingAtSymbol_currentBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.emailMissingAtSymbol(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithEmailMissingAtSymbol_currentBehavior] Response:\n{}",
                response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchMalformedLoginServerError()
                .verify();
    }

    @Test
    @Disabled("API behavior not yet aligned with expected contract")
    @Tag("disabledUntilFix")
    @Story("Login with malformed email (missing '@')")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with email missing '@' symbol — expected behavior")
    void testLoginWithEmailMissingAtSymbol_expectedBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.emailMissingAtSymbol(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        LoginSoftAssert.softAssert(response)
                .shouldMatchInvalidEmailFormatError()
                .verify();
    }

    @Test
    @Tag("brokenApi")
    @DisplayName("API: Login with malformed email — current behavior (known issue)")
    @Story("Login with malformed email (missing domain)")
    @Severity(SeverityLevel.CRITICAL)
    void testLoginWithEmailMissingDomain_currentBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.emailMissingDomain(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithEmailMissingDomain_currentBehavior] Response:\n{}",
                response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchMalformedLoginServerError()
                .verify();
    }

    @Test
    @Disabled("API behavior not yet aligned with expected contract")
    @Tag("disabledUntilFix")
    @DisplayName("API: Login with malformed email — expected behavior")
    @Story("Login with malformed email (missing domain)")
    @Severity(SeverityLevel.CRITICAL)
    void testLoginWithMalformedEmail_expectedBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.emailMissingDomain(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithMalformedEmail_expectedBehavior] Response:\n{}",
                response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchInvalidEmailFormatError()
                .verify();
    }

    @Test
    @Tag("brokenApi")
    @Story("Login with empty email")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with empty email — current behavior (known issue)")
    void testLoginWithEmptyEmail_currentBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.emptyEmail(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithEmptyEmail_currentBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchMalformedLoginServerError()
                .verify();
    }

    @Test
    @Disabled("API behavior not yet aligned with expected contract")
    @Tag("disabledUntilFix")
    @Story("Login with empty email")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with empty email — expected behavior")
    void testLoginWithEmptyEmail_expectedBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.emptyEmail(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithEmptyEmail_expectedBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchEmptyEmailError()
                .verify();
    }

    @Test
    @Tag("brokenApi")
    @Story("Login with email containing trailing space")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with email containing trailing space — current behavior (known issue)")
    void testLoginWithEmailTrailingSpace_currentBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.emailWithTrailingSpace(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithEmailTrailingSpace_currentBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchMalformedLoginServerError()
                .verify();
    }

    @Test
    @Disabled("API behavior not yet aligned with expected contract")
    @Tag("disabledUntilFix")
    @Story("Login with email containing trailing space")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with email containing trailing space — expected behavior")
    void testLoginWithEmailTrailingSpace_expectedBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.emailWithTrailingSpace(),
                TestCredentialsFactory.randomPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithEmailTrailingSpace_expectedBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchInvalidEmailFormatError()
                .verify();
    }

    @Test
    @Tag("brokenApi")
    @Story("Login with password containing only letters")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with password containing only letters — current behavior (known issue)")
    void testLoginWithPasswordOnlyLetters_currentBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.passwordWithOnlyLetters()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithPasswordOnlyLetters_currentBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchMalformedLoginServerError()
                .verify();
    }

    @Test
    @Disabled("API behavior not yet aligned with expected contract")
    @Tag("disabledUntilFix")
    @Story("Login with password containing only letters")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with password containing only letters — expected behavior")
    void testLoginWithPasswordOnlyLetters_expectedBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.passwordWithOnlyLetters()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithPasswordOnlyLetters_expectedBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchInvalidPasswordFormatError()
                .verify();
    }

    @Test
    @Tag("brokenApi")
    @Story("Login with empty password")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with empty password — current behavior (known issue)")
    void testLoginWithEmptyPassword_currentBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.emptyPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithEmptyPassword_currentBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchMalformedLoginServerError()
                .verify();
    }

    @Test
    @Disabled("API behavior not yet aligned with expected contract")
    @Tag("disabledUntilFix")
    @Story("Login with empty password")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with empty password — expected behavior")
    void testLoginWithEmptyPassword_expectedBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.emptyPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithEmptyPassword_expectedBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchEmptyPasswordError()
                .verify();
    }

    @Test
    @Tag("brokenApi")
    @Story("Login with short password")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with short password — current behavior (known issue)")
    void testLoginWithShortPassword_currentBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.shortPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithShortPassword_currentBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchMalformedLoginServerError()
                .verify();
    }

    @Test
    @Disabled("API behavior not yet aligned with expected contract")
    @Tag("disabledUntilFix")
    @Story("Login with short password")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("API: Login with short password — expected behavior")
    void testLoginWithShortPassword_expectedBehavior() {
        LoginRequest request = new LoginRequest(
                TestCredentialsFactory.validEmail(),
                TestCredentialsFactory.shortPassword()
        );

        Response response = authClient.login(request);
        log.info("[testLoginWithShortPassword_expectedBehavior] Response:\n{}", response.getBody().asPrettyString());

        LoginSoftAssert.softAssert(response)
                .shouldMatchShortPasswordError()
                .verify();
    }
}