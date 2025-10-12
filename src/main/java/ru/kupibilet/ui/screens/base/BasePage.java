package ru.kupibilet.ui.screens.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.utils.ui.WaitUtils;

import java.util.List;

import static ru.kupibilet.utils.ui.SensitiveFieldRegistry.isSensitive;

public abstract class BasePage {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Finds the first element matching the given locator.
     *
     * @param locator the By selector
     * @return WebElement if found
     */
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Finds all elements matching the given locator.
     *
     * @param locator the By selector
     * @return list of matching WebElements
     */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    protected void click(By locator) {
        WaitUtils.waitUntilClickable(driver, locator);
        log.info("Clicking element [{}]", locator);
        find(locator).click();
    }

    protected void type(By locator, String text) {
        if (isSensitive(locator)) {
            log.info("Typing [REDACTED] into element [{}]", locator);
        } else {
            log.info("Typing '{}' into element [{}]", text, locator);
        }
        find(locator).sendKeys(text);
    }

    public String getText(By locator) {
        try {
            String text = find(locator).getText();
            log.info("Getting text from element [{}]: '{}'", locator, text);
            return text;
        } catch (NoSuchElementException e) {
            log.warn("Element [{}] not found for getText()", locator);
            return "";
        }
    }

    public boolean isVisible(By locator) {
        return find(locator).isDisplayed();
    }

    public boolean isPresent(By locator) {
        return !findAll(locator).isEmpty();
    }

    public boolean isEnabled(By locator) {
        return find(locator).isEnabled();
    }
}
