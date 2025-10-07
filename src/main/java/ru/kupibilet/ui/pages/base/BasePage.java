package ru.kupibilet.ui.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.utils.EnvironmentConfig;

import static ru.kupibilet.ui.utils.SensitiveFieldRegistry.isSensitive;

public abstract class BasePage {

    protected static final String BASE_URL = EnvironmentConfig.BASE_URL;

    protected WebDriver driver;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void click(By locator) {
        log.info("Clicking element [{}]", locator);
        driver.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        if (isSensitive(locator)) {
            log.info("Typing [REDACTED] into element [{}]", locator);
        } else {
            log.info("Typing '{}' into element [{}]", text, locator);
        }
        driver.findElement(locator).sendKeys(text);
    }

    public String getText(By locator) {
        String text = driver.findElement(locator).getText();
        log.info("Getting text from element [{}]: '{}'", locator, text);
        return text;
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }
}
