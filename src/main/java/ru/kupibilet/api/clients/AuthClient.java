package ru.kupibilet.api.clients;

import ru.kupibilet.api.models.LoginRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private static final String URL = "https://flights-api-orders.kupibilet.ru/account/sign_in.json";

    public static Response login(LoginRequest request) {
        return given()
                .contentType("application/json")
                .body(request)
                .post(URL);
    }
}
