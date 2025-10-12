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
│   │   │   ├── AuthClient.java
│   │   │   └── BaseApiClient.java                  # Shared HTTP logic and logging
│   │   ├── models/                                 # Request/response models for API communication
│   │   │   └── LoginRequest.java
│   │   └── utils/                                  # API-specific utilities
│   │       └── ApiLogUtils.java                    # Logging helpers for API requests/responses

│   ├── auth/                                       # Domain logic for authentication
│   │   └── dto/                                    # Credential handling and identity transfer
│   │       └── Credentials.java

│   ├── config/                                     # Global and environment-specific configuration
│   │   ├── Config.java                             # Global settings: API URLs, timeouts, flags
│   │   ├── BrowserType.java                        # Supported browser types used in UI automation
│   │   ├── BrowserConfig.java                      # UI-specific settings: browser type, headless mode, window size
│   │   └── ApiConfig.java                          # API-specific settings: base URLs, tokens, headers

│   ├── search/                                     # Domain logic for flight search
│   │   └── dto/                                    # Data transfer objects for search queries
│   │       ├── FlightSearchQuery.java
│   │       └── TravelClass.java

│   ├── testdata/                                   # Unified test data generators for all domains
│   │   ├── TestCredentialsFactory.java             # Factory for login credentials
│   │   └── FlightSearchDataFactory.java            # Factory for flight search scenarios

│   ├── ui/                                         # UI layer: page objects and automation logic
│   │   ├── components/                             # Reusable UI components
│   │   │   ├── base/                               # Base classes for components and dialogs
│   │   │   │   ├── BaseComponent.java
│   │   │   │   └── BaseDialogComponent.java
│   │   │   ├── Header.java
│   │   │   ├── TicketAirplaneCard.java
│   │   │   ├── FlightSegment.java
│   │   │   └── interfaces/
│   │   │       └── Clickable.java                  # Interface for clickable UI components only
│   │   ├── drivers/                                # WebDriver initialization and management
│   │   │   └── SeleniumDriverFactory.java
│   │   ├── exceptions/                             # Custom exceptions for UI failures
│   │   │   └── DriverInitializationException.java
│   │   ├── screens/                                # Page object model structure (full pages)
│   │   │   ├── base/                               # Base class for all screens
│   │   │   │   └── BasePage.java
│   │   │   ├── HomePage.java
│   │   │   └── SearchResultsPage.java
│   │   └── popups/                                 # Dialogs and overlays
│   │       ├── LoginDialog.java
│   │       └── TicketDetailsDialog.java

│   └── utils/                                      # Shared utilities across domains
│       ├── api/                                    # API-specific helpers and logging
│       │   └── ApiLogUtils.java                    # Logging and formatting for API requests/responses
│       ├── ui/                                     # UI-specific helper classes
│       │   ├── EnvironmentConfig.java              # Environment-based UI settings
│       │   ├── SensitiveFieldRegistry.java         # Registry of sensitive fields for masking
│       │   └── WaitUtils.java                      # Explicit and fluent wait utilities
│       ├── TransferUtils.java                      # Utility for parsing transfer count from trip text
│       ├── LoginErrorTemplates.java                # Centralized login error messages and codes
│       └── LoginResponseFields.java                # Constants for expected response fields

├── test/
│   ├── java/ru/kupibilet/                          # Test classes and scenarios
│   │   ├── api/                                    # API-level tests
│   │   │   ├── NegativeLoginApiTest.java           # Negative login test cases
│   │   │   ├── steps/                              # Step-based API flows
│   │   │   │   └── LoginSteps.java
│   │   │   ├── assertions/                         # Reusable API assertions
│   │   │   │   ├── LoginAssertions.java
│   │   │   │   └── LoginSoftAssert.java            # Fluent-style soft assertions for login errors
│   │   │   └── fluent/                             # Fluent-style API DSL
│   │   │       └── FluentApiTest.java
│   │   └── ui/                                     # UI-level tests
│   │       ├── BaseTest.java
│   │       ├── LoginTest.java
│   │       ├── FlightSearchTest.java
│   │       ├── steps/
│   │       │   └── FlightSearchSteps.java          # Fluent interface for flight search actions
│   │       └── assertions/
│   │           └── FlightAssertions.java           # Reusable assertions for flight-related checks

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
