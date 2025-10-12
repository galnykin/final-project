package ru.kupibilet.utils;

public class LoginErrorTemplates {

    private LoginErrorTemplates() {
    }

    public static final String INVALID_EMAIL_MESSAGE = "Email format is invalid";
    public static final String INVALID_EMAIL_CODE = "AUTH_INVALID_EMAIL_FORMAT";

    public static final String EMPTY_EMAIL_MESSAGE = "Email must not be empty";
    public static final String EMPTY_EMAIL_CODE = "AUTH_EMPTY_EMAIL";

    public static final String INVALID_PASSWORD_MESSAGE = "Password format is invalid";
    public static final String INVALID_PASSWORD_CODE = "AUTH_INVALID_PASSWORD_FORMAT";

    public static final String EMPTY_PASSWORD_MESSAGE = "Password must not be empty";
    public static final String EMPTY_PASSWORD_CODE = "AUTH_EMPTY_PASSWORD";

    public static final String SHORT_PASSWORD_MESSAGE = "Password is too short";
    public static final String SHORT_PASSWORD_CODE = "AUTH_PASSWORD_TOO_SHORT";

    public static final String UNREGISTERED_USER_MESSAGE = "Wrong email or password";
    public static final String UNREGISTERED_USER_CODE = "AUTH_INVALID_CREDENTIALS";

    public static final String INTERNAL_BACKEND_MESSAGE = "internalBackendError";
    public static final String SERVER_ERROR_TYPE = "serverError";

    public static final String AUTH_ERROR_TYPE = "authError";
}
