package ru.kupibilet.api.assertions;

import io.restassured.response.Response;
import org.assertj.core.api.SoftAssertions;
import ru.kupibilet.utils.LoginErrorTemplates;
import ru.kupibilet.utils.LoginResponseFields;

public class LoginSoftAssert {

    private final Response response;
    private final SoftAssertions softly = new SoftAssertions();

    public LoginSoftAssert(Response response) {
        this.response = response;
    }

    public static LoginSoftAssert softAssert(Response response) {
        return new LoginSoftAssert(response);
    }

    public LoginSoftAssert shouldHaveStatusCode(int expectedStatus) {
        softly.assertThat(response.getStatusCode())
                .as("Status code")
                .isEqualTo(expectedStatus);
        return this;
    }

    public LoginSoftAssert shouldHaveError(String expectedError) {
        softly.assertThat(response.jsonPath().getString("error"))
                .as("Error field")
                .isEqualTo(expectedError);
        return this;
    }

    public LoginSoftAssert shouldHaveMessage(String expectedMessage) {
        softly.assertThat(response.jsonPath().getString("message"))
                .as("Message field")
                .isEqualTo(expectedMessage);
        return this;
    }

    public LoginSoftAssert shouldHaveErrorCode(String expectedCode) {
        softly.assertThat(response.jsonPath().getString("errorCode"))
                .as("Error code")
                .isEqualTo(expectedCode);
        return this;
    }

    public LoginSoftAssert shouldNotContainToken() {
        softly.assertThat(response.jsonPath().getString("accessToken"))
                .as("Access token")
                .isNull();
        softly.assertThat(response.jsonPath().getString("refreshToken"))
                .as("Refresh token")
                .isNull();
        return this;
    }

    public LoginSoftAssert shouldHaveField(String fieldName) {
        softly.assertThat(response.jsonPath().getString(fieldName))
                .as("Field '%s' should be present", fieldName)
                .isNotNull();
        return this;
    }

    public LoginSoftAssert shouldNotExposeStackTrace() {
        String body = response.getBody().asString();
        softly.assertThat(body)
                .as("Stack trace leak")
                .doesNotContain("Exception")
                .doesNotContain("at ");
        return this;
    }

    public LoginSoftAssert shouldNotHaveField(String fieldName) {
        softly.assertThat(response.jsonPath().getString(fieldName))
                .as("Field '%s' should be null", fieldName)
                .isNull();
        return this;
    }

    public LoginSoftAssert shouldMatchMalformedLoginServerError(String expectedMessage) {
        return this
                .shouldHaveStatusCode(200)
                .shouldHaveError(LoginErrorTemplates.SERVER_ERROR_TYPE)
                .shouldHaveMessage(expectedMessage)
                .shouldHaveErrorCode(null)
                .shouldNotContainToken()
                .shouldNotHaveField(LoginResponseFields.TIMESTAMP)
                .shouldNotHaveField(LoginResponseFields.PATH)
                .shouldNotExposeStackTrace();
    }

    public LoginSoftAssert shouldMatchMalformedLoginServerError() {
        return shouldMatchMalformedLoginServerError(LoginErrorTemplates.INTERNAL_BACKEND_MESSAGE);
    }

    public LoginSoftAssert shouldMatchInvalidEmailFormatError(String expectedMessage) {
        return this
                .shouldHaveStatusCode(400)
                .shouldHaveError(LoginErrorTemplates.AUTH_ERROR_TYPE)
                .shouldHaveMessage(expectedMessage)
                .shouldHaveErrorCode(LoginErrorTemplates.INVALID_EMAIL_CODE)
                .shouldNotContainToken()
                .shouldHaveField(LoginResponseFields.TIMESTAMP)
                .shouldHaveField(LoginResponseFields.PATH)
                .shouldNotExposeStackTrace();
    }

    public LoginSoftAssert shouldMatchInvalidEmailFormatError() {
        return shouldMatchInvalidEmailFormatError(LoginErrorTemplates.INVALID_EMAIL_MESSAGE);
    }

    public LoginSoftAssert shouldMatchInvalidPasswordFormatError(String expectedMessage) {
        return this
                .shouldHaveStatusCode(400)
                .shouldHaveError(LoginErrorTemplates.AUTH_ERROR_TYPE)
                .shouldHaveMessage(expectedMessage)
                .shouldHaveErrorCode(LoginErrorTemplates.INVALID_PASSWORD_CODE)
                .shouldNotContainToken()
                .shouldHaveField(LoginResponseFields.TIMESTAMP)
                .shouldHaveField(LoginResponseFields.PATH)
                .shouldNotExposeStackTrace();
    }

    public LoginSoftAssert shouldMatchInvalidPasswordFormatError() {
        return shouldMatchInvalidPasswordFormatError(LoginErrorTemplates.INVALID_PASSWORD_MESSAGE);
    }

    public LoginSoftAssert shouldMatchEmptyPasswordError() {
        return this
                .shouldHaveStatusCode(400)
                .shouldHaveError(LoginErrorTemplates.AUTH_ERROR_TYPE)
                .shouldHaveMessage(LoginErrorTemplates.EMPTY_PASSWORD_MESSAGE)
                .shouldHaveErrorCode(LoginErrorTemplates.EMPTY_PASSWORD_CODE)
                .shouldNotContainToken()
                .shouldHaveField(LoginResponseFields.TIMESTAMP)
                .shouldHaveField(LoginResponseFields.PATH)
                .shouldNotExposeStackTrace();
    }

    public LoginSoftAssert shouldMatchShortPasswordError() {
        return this
                .shouldHaveStatusCode(400)
                .shouldHaveError(LoginErrorTemplates.AUTH_ERROR_TYPE)
                .shouldHaveMessage(LoginErrorTemplates.SHORT_PASSWORD_MESSAGE)
                .shouldHaveErrorCode(LoginErrorTemplates.SHORT_PASSWORD_CODE)
                .shouldNotContainToken()
                .shouldHaveField(LoginResponseFields.TIMESTAMP)
                .shouldHaveField(LoginResponseFields.PATH)
                .shouldNotExposeStackTrace();
    }

    public LoginSoftAssert shouldMatchUnregisteredUserError() {
        return this
                .shouldHaveStatusCode(401)
                .shouldHaveError(LoginErrorTemplates.AUTH_ERROR_TYPE)
                .shouldHaveMessage(LoginErrorTemplates.UNREGISTERED_USER_MESSAGE)
                .shouldHaveErrorCode(LoginErrorTemplates.UNREGISTERED_USER_CODE)
                .shouldNotContainToken()
                .shouldHaveField(LoginResponseFields.TIMESTAMP)
                .shouldHaveField(LoginResponseFields.PATH)
                .shouldNotExposeStackTrace();
    }

    public LoginSoftAssert shouldMatchEmptyEmailError() {
        return this
                .shouldHaveStatusCode(400)
                .shouldHaveError(LoginErrorTemplates.AUTH_ERROR_TYPE)
                .shouldHaveMessage(LoginErrorTemplates.EMPTY_EMAIL_MESSAGE)
                .shouldHaveErrorCode(LoginErrorTemplates.EMPTY_EMAIL_CODE)
                .shouldNotContainToken()
                .shouldHaveField(LoginResponseFields.TIMESTAMP)
                .shouldHaveField(LoginResponseFields.PATH)
                .shouldNotExposeStackTrace();
    }

    public void verify() {
        softly.assertAll();
    }
}
