package ru.kupibilet.api.assertions;

import io.restassured.response.Response;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginAssertions {

    public static void assertLoginReturns200(Response response) {
        assertEquals(200, response.getStatusCode(), "Expected status code 200");
    }

    public static void assertErrorMessageEquals(Response response, String expectedMessage) {
        String actualMessage = response.jsonPath().getString("message");
        assertEquals(expectedMessage, actualMessage, "Error message mismatch");
    }

    public static void assertErrorCode(Response response, String expectedCode) {
        String actualCode = response.jsonPath().getString("errorCode");
        assertEquals(expectedCode, actualCode, "Error code mismatch");
    }

    public static void assertNoAuthToken(Response response) {
        assertNull(response.jsonPath().getString("accessToken"), "Access token should not be present");
        assertNull(response.jsonPath().getString("refreshToken"), "Refresh token should not be present");
    }

    public static void assertResponseTimeUnder(Response response, long maxMillis) {
        assertTrue(response.getTime() < maxMillis, "Response time exceeded " + maxMillis + "ms");
    }

    public static void assertContentTypeIsJson(Response response) {
        assertEquals("application/json", response.getContentType(), "Content-Type is not JSON");
    }

    public static void assertNoSetCookieHeader(Response response) {
        List<String> cookies = response.getHeaders().getValues("Set-Cookie");
        assertTrue(cookies == null || cookies.isEmpty(), "Unexpected Set-Cookie header found");
    }

    public static void assertResponseHasFields(Response response, String... fields) {
        for (String field : fields) {
            assertNotNull(response.jsonPath().getString(field), "Missing field: " + field);
        }
    }

    public static void assertNoStackTraceLeak(Response response) {
        String body = response.getBody().asString();
        assertFalse(body.contains("Exception"), "Stack trace leak detected");
        assertFalse(body.contains("at "), "Stack trace leak detected");
    }
}
