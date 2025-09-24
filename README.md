## 📦 Kupibilet Test Automation Framework

Автоматизированный фреймворк для тестирования веб-интерфейса и API платформы Kupibilet. Построен на основе Java, Maven, JUnit 5, Selenium WebDriver и Rest Assured.

---

### 🚀 Стек технологий

- **Java 17+**
- **Maven**
- **JUnit 5**
- **Selenium WebDriver**
- **Rest Assured**
- **AssertJ** (для soft assert'ов)
- **Bonigarcia WebDriverManager**

---

### 📁 Структура проекта

```
src
├── main
│   └── java
│       ├── ru.kupibilet.ui        # UI-слой: страницы, драйверы, локаторы
│       └── ru.kupibilet.api       # API-слой: клиенты, модели, конфигурация
├── test
│   └── java
│       ├── ui                     # UI-тесты
│       └── api                    # API-тесты
```

---

### ⚙️ Конфигурация

Все параметры хранятся в `config.properties`

---

### 🧪 Запуск тестов

```
mvn test
```

---

### 🧰 Особенности

- 📌 **DriverManager** — управление WebDriver
- 📌 **BasePage** — общие действия с элементами
- 📌 **Locator Repository** — локаторы вынесены в отдельные классы
- 📌 **ValidationHelper / ApiResponseValidator** — централизованные проверки

---