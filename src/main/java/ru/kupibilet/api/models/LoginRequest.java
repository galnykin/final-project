package ru.kupibilet.api.models;

import ru.kupibilet.auth.dto.Credentials;

/**
 * Request model for login API.
 */
public class LoginRequest {

    /** Default API version for login requests */
    public static final String DEFAULT_VERSION = "2.0";

    private String email;
    private String password;
    private String version = DEFAULT_VERSION;

    public LoginRequest(String email, String password, String version) {
        this.email = email;
        this.password = password;
        this.version = version;
    }

    public LoginRequest(String email, String password) {
        this(email, password, DEFAULT_VERSION);
    }

    public static LoginRequest from(Credentials credentials) {
        return new LoginRequest(credentials.getEmail(), credentials.getPassword());
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getVersion() {
        return version;
    }
}
