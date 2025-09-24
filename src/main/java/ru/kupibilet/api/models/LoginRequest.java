package ru.kupibilet.api.models;

public class LoginRequest {
    public String email;
    public String password;
    public String v = "2.0";

    public LoginRequest(String email, String password, String version) {
        this.email = email;
        this.password = password;
        this.v = version;
    }

    public LoginRequest(String email, String password) {
        new LoginRequest(email, password, this.v);
    }
}
