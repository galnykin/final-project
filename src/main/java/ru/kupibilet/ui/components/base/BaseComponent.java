package ru.kupibilet.ui.components.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.utils.ui.WaitUtils;

import java.util.List;

public abstract class BaseComponent {

    protected final WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(BaseComponent.class);

    public BaseComponent(WebDriver driver) {
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
        find(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }

    protected boolean isVisible(By locator) {
        return find(locator).isDisplayed();
    }

    protected boolean isEnabled(By locator) {
        return find(locator).isEnabled();
    }
}