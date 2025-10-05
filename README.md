
## Kupibilet Test Automation Framework

Automated framework for UI and API testing of the Kupibilet web platform. Built using Java, Maven, JUnit 5, Selenium WebDriver, and Rest Assured.

---

### Technology Stack

- Java 17+
- Maven
- JUnit 5
- Selenium WebDriver
- Rest Assured
- AssertJ (for soft assertions)
- Bonigarcia WebDriverManager

---

### Project Structure

```
src
├── main
│   └── java
│       ├── ru.kupibilet.ui        // UI layer: pages, drivers, locators
│       └── ru.kupibilet.api       // API layer: clients, models, configuration
├── test
│   └── java
│       ├── ui                     // UI tests
│       └── api                    // API tests
```

---

### Configuration

All runtime parameters are stored in `config.properties`.

---

### Test Execution

To run all tests:

```
mvn clean test
```

---

### Framework Features

- DriverManager: centralized WebDriver setup
- BasePage: common UI actions and utilities
- Locator Repository: locators organized in separate classes

---

### Reporting

- Allure CLI is installed and integrated via Jenkins
- Test results are saved to `target/allure-results`
- Reports are generated and displayed in Jenkins
- Report artifacts are archived after each build

---

### CI/CD Integration

- Declarative Jenkins pipeline is configured
- Jenkinsfile is adapted for Windows agents
- Maven and Allure tools are preconfigured
- Tests are executed daily at a scheduled time via Jenkins
- Allure reports are generated and archived after each scheduled run
