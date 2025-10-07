package ru.kupibilet.auth.testdata;

import ru.kupibilet.auth.dto.Credentials;
import ru.kupibilet.ui.utils.ConfigReader;

public class TestCredentialsFactory {

    public static Credentials validUser() {
        return new Credentials(
                ConfigReader.get("valid.email"),
                ConfigReader.get("valid.password"));
    }

    public static Credentials emptyFields() {
        return new Credentials("", "");
    }

    public static Credentials invalidEmailFormat() {
        return new Credentials("invalid.email", "123456");
    }

    public static Credentials missingPassword() {
        return new Credentials("user@example.com", "");
    }

    public static Credentials invalidCredentials() {
        return new Credentials("invalidUser@example.com", "3049urfokj304944");
    }
}
