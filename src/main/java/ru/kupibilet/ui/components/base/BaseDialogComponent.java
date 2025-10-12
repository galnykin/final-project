package ru.kupibilet.ui.components.base;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.utils.ui.WaitUtils;

import java.util.List;

public abstract class BaseDialogComponent extends BaseComponent {

    public static final By ROOT = By.cssSelector("section[role='dialog'][aria-modal='true']");
    protected final By overlay = By.className("styled__Overlay-sc-obgdhz-0");
    protected By closeButton = By.cssSelector("button[class=" +
            "'styled__StyledButton-sc-rogkzj-0 flJyPy styled__StyledCloseButton-sc-obgdhz-4 eFdaht']");

    private static final Logger log = LoggerFactory.getLogger(BaseDialogComponent.class);

    public BaseDialogComponent(WebDriver driver) {
        super(driver);
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

    public boolean isVisible(By rootLocator) {
        try {
            WebElement root = driver.findElement(rootLocator);
            return root.isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public boolean isHidden(By rootLocator) {
        return !isVisible(rootLocator);
    }

    public void waitUntilVisible(By rootLocator) {
        log.info("Ожидаем появления диалога: {}", rootLocator);
        WaitUtils.waitUntilVisible(driver, rootLocator);
    }

    public void waitUntilHidden(By rootLocator) {
        log.info("Ожидаем скрытия диалога: {}", rootLocator);
        WaitUtils.waitUntilInvisible(driver, rootLocator);
    }

    public void close() {
        if (isOverlayVisible()) {
            log.info("Закрытие диалога через overlay");
            click(overlay);
            return;
        }

        if (isCloseButtonVisible()) {
            log.info("Закрытие диалога через крестик");
            click(closeButton);
            return;
        }

        throw new IllegalStateException("Невозможно закрыть диалог: overlay и крестик не найдены");
    }

    protected boolean isClosed(By rootLocator) {
        try {
            WebElement root = driver.findElement(rootLocator);
            return !root.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    public void closeByClickOutside() {
        click(overlay);
    }

    public boolean isOverlayVisible() {
        return isVisible(overlay);
    }

    protected boolean isCloseButtonVisible() {
        return isVisible(getCloseButton());
    }

    protected By getCloseButton() {
        return closeButton;
    }
}
