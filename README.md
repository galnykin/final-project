## Kupibilet Test Automation Framework

Automated framework for UI and API testing of the Kupibilet web platform. Built using Java, Maven, JUnit 5, Selenium
WebDriver, and Rest Assured. Designed with modular architecture for scalability, maintainability, and clear separation
of concerns.

---

### Technology Stack

- Java 17+
- Maven
- JUnit 5
- Selenium WebDriver
- Rest Assured
- Allure Reporting
- Jenkins CI/CD

---

### Core Files

At the root of the project:

- `pom.xml` – Maven build configuration
- `Jenkinsfile` – CI/CD pipeline definition
- `README.md` – Project documentation

---

### Project Structure

```
src/
├── main/java/ru/kupibilet/
│   ├── api/                                        # Integration layer for external APIs
│   │   ├── clients/                                # HTTP clients or service wrappers
│   │   │   └── AuthClient.java
│   │   └── models/                                 # Request/response models for API communication
│   │       └── LoginRequest.java

│   ├── auth/                                       # Domain logic for authentication
│   │   └── dto/                                    # Credential handling and identity transfer
│   │       └── Credentials.java

│   ├── search/                                     # Domain logic for flight search
│   │   └── dto/                                    # Data transfer objects for search queries
│   │       ├── FlightSearchQuery.java
│   │       └── TravelClass.java

│   ├── testdata/                                   # Unified test data generators for all domains
│   │   ├── TestCredentialsFactory.java
│   │   └── FlightSearchDataFactory.java

│   ├── ui/                                         # UI layer: page objects and automation logic
│   │   ├── config/                                 # Browser and environment configuration
│   │   │   ├── BrowserType.java
│   │   │   └── Config.java
│   │   ├── drivers/                                # WebDriver initialization and management
│   │   │   └── SeleniumDriverFactory.java
│   │   ├── exceptions/                             # Custom exceptions for UI failures
│   │   │   └── DriverInitializationException.java
│   │   ├── screens/                                # Page object model structure (full pages)
│   │   │   ├── base/                               # Base class for all screens
│   │   │   │   └── BasePage.java
│   │   │   ├── HomePage.java
│   │   │   └── SearchResultsPage.java
│   │   ├── components/                             # Reusable UI components
│   │   │   ├── base/                               # Base classes for components and dialogs
│   │   │   │   ├── BaseComponent.java
│   │   │   │   └── BaseDialogComponent.java
│   │   │   ├── HeaderComponent.java
│   │   │   └── TicketCardComponent.java
│   │   └── popups/                                 # Dialogs and overlays
│   │       ├── LoginDialog.java
│   │       └── TicketDetailsDialog.java

│   └── utils/
│       └── ui/                                     # UI-specific helper classes
│           ├── EnvironmentConfig.java
│           ├── SensitiveFieldRegistry.java
│           └── WaitUtils.java

├── test/          
│   ├── java/                                       # Test classes and scenarios
│   │   ├── api/                                    # API-level tests
│   │   │   ├── BaseApiClient.java
│   │   │   └── LoginApiTest.java
│   │   └── ui/                                     # UI-level tests
│   │       ├── BaseTest.java
│   │       ├── LoginTest.java
│   │       └── FlightSearchTest.java

│   └── resources/                                  # Resources for test execution
│       └── allure.properties                       # Allure reporting configuration

resources/
└── log4j2.xml                                      # Logging configuration
```

---

### Configuration

- Runtime parameters are managed via `EnvironmentConfig`
- Browser type and base URL are configured in `Config` and `BrowserType`
- Browser selection is dynamic and controlled via system property `-Dbrowser`
- Enum `BrowserType` defines supported browsers
- `SeleniumDriverFactory` reads the property using `System.getProperty("browser")`
- This allows flexible browser selection for CI and cross-browser testing

Example:

```bash
mvn clean test -Dbrowser=firefox
```

---

### Logging

- Logging is implemented using SLF4J with Log4j2 binding
- Each class defines its own static logger instance
- Log messages are used for key lifecycle events
- Logging configuration is defined in `resources/log4j2.xml`

---

### BaseTest Lifecycle

All UI tests inherit from `BaseTest`, which provides:

- WebDriver initialization via `SeleniumDriverFactory.getDriver()`
- Navigation to the configured `baseUrl` before each test
- Graceful teardown after each test using `quitDriver()`

---

### Locator Strategy

- Locators are defined within base classes such as `BasePage`, `BaseComponent`, and `BaseDialogComponent`
- There is no centralized locator repository

---

### Test Execution

To run all tests:

```bash
mvn clean test
```

To run a specific test class:

```bash
mvn -Dtest=LoginTest test
```

---

### Framework Features

- **BaseTest**: provides WebDriver lifecycle management and logging
- **BasePage & BaseComponent**: reusable UI actions and structure
- **Test Data Factories**: centralized generation of test inputs
- **Modular Architecture**: separation between API, UI, domain, and test logic

---

### Reporting

- Allure results are saved to `target/allure-results`
- Reports are generated and published via Jenkins
- `allure.properties` configures report metadata

---

### CI/CD Integration

- Jenkins executes tests via declarative pipeline (`Jenkinsfile`)
- Pipeline supports scheduled and manual runs
- Allure reports are generated and archived after each run

---
