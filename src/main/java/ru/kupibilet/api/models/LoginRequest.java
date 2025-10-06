package ru.kupibilet.api.models;

public class LoginRequest {
    private String email;
    private String password;
    private String v = "2.0";

    public LoginRequest(String email, String password, String version) {
        this.email = email;
        this.password = password;
        this.v = version;
    }

    public LoginRequest(String email, String password) {
        this(email, password, "2.0");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getVersion() {
        return v;
    }
}

