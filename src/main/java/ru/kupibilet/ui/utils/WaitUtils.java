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
 */
public class WaitUtils {

    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

    /**
     * Waits until the specified element becomes visible on the page.
     * <p>
     * Useful for detecting that dynamic content (e.g. autocomplete dropdowns, loaders)
     * has finished loading and is ready for interaction.
     *
     * @param driver  WebDriver instance
     * @param locator locator of the element to wait for
     * @return true if the element becomes visible within the timeout
     */
    public static boolean waitUntilVisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.visibilityOfElementLocated(locator)) != null;
    }

    /**
     * Waits until the element located by the given locator becomes clickable.
     *
     * @param driver  the WebDriver instance
     * @param locator the locator of the element
     * @return the WebElement once it is clickable
     */
    public static WebElement waitUntilClickable(WebDriver driver, By locator) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the element located by the given locator becomes invisible or is removed from the DOM.
     *
     * @param driver  the WebDriver instance
     * @param locator the locator of the element
     * @return true if the element becomes invisible
     */
    public static boolean waitUntilInvisible(WebDriver driver, By locator) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits until the specified text appears within the element located by the given locator.
     *
     * @param driver  the WebDriver instance
     * @param locator the locator of the element
     * @param text    the expected text to appear
     * @return true if the text appears within the element
     */
    public static boolean waitUntilTextPresent(WebDriver driver, By locator, String text) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Waits until the specified element becomes visible on the page.
     *
     * @param driver  WebDriver instance
     * @param locator locator of the element to wait for
     * @return visible WebElement
     */
    public static WebElement waitForVisibility(WebDriver driver, By locator) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
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
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
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
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
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
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
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
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(ExpectedConditions.textToBePresentInElementLocated(locator, expectedText));
    }

    /**
     * Waits until the specified input element contains the expected value in its "value" attribute.
     * <p>
     * Useful for verifying that asynchronous input (e.g. autocomplete, delayed typing) has completed
     * before proceeding with further actions like clicking or submitting.
     *
     * @param driver        WebDriver instance
     * @param locator       locator of the input element to wait for
     * @param expectedValue value to wait for in the input field
     * @return true if the value appears within the timeout
     */
    public static boolean waitUntilInputValueEquals(WebDriver driver, By locator, String expectedValue) {
        return new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(d -> expectedValue.equals(d.findElement(locator).getAttribute("value")));
    }
}
