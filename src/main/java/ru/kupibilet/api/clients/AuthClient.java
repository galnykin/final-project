package ru.kupibilet.api.clients;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.api.models.LoginRequest;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class AuthClient {

    private static final String URL = "https://flights-api-orders.kupibilet.ru/account/sign_in.json";
    private static final Logger log = LoggerFactory.getLogger(AuthClient.class);

    public static Response login(LoginRequest request) {
        log.info("Отправка запроса авторизации для email: {}", request.email);
        return given()
                .contentType("application/json")
                .body(request)
                .post(URL);
    }
}
