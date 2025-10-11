package ru.kupibilet.ui.components.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseComponent {

    protected final WebDriver driver;
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
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