package ru.kupibilet.ui.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class for common WebDriver wait operations.
 * Provides reusable methods for explicit waits such as visibility, clickability, presence, and disappearance.
 */
public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 5;

    /**
     * Waits until the specified element becomes visible on the page.
     *
     * @param driver  WebDriver instance
     * @param locator locator of the element to wait for
     * @return visible WebElement
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the specified element becomes visible on the page using a custom timeout.
     *
     * @param driver         WebDriver instance
     * @param locator        locator of the element to wait for
     * @param timeoutSeconds maximum wait time in seconds
     * @return visible WebElement
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator, int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Checks whether the element located by the given locator is visible on the page.
     *
     * @param driver  WebDriver instance
     * @param locator locator of the element to check
     * @return true if the element is visible, false otherwise
     */
    public static boolean isElementVisible(WebDriver driver, By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Waits until the specified element is present in the DOM, regardless of its visibility.
     *
     * @param driver  WebDriver instance
     * @param locator locator of the element to wait for
     * @return present WebElement
     */
    public static WebElement waitForPresence(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits until the specified element is clickable.
     *
     * @param driver  WebDriver instance
     * @param locator locator of the element to wait for
     * @return clickable WebElement
     */
    public static WebElement waitForClickability(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the specified element disappears from the page.
     *
     * @param driver  WebDriver instance
     * @param locator locator of the element to wait for
     * @return true if the element disappears within the timeout
     */
    public static boolean waitForInvisibility(WebDriver driver, By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits until the specified element contains the expected text.
     *
     * @param driver       WebDriver instance
     * @param locator      locator of the element to wait for
     * @param expectedText text to wait for
     * @return true if the text appears within the timeout
     */
    public static boolean waitForText(WebDriver driver, By locator, String expectedText) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }
}
