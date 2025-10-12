package ru.kupibilet.config;

public class ApiConfig {
    public static String getLoginApiUrl() {
        return System.getProperty("loginApiUrl", "https://flights-api-orders.kupibilet.ru/account/sign_in.json");
    }
}
