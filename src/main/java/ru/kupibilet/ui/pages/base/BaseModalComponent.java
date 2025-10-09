package ru.kupibilet.ui.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.kupibilet.ui.utils.WaitUtils;

public abstract class BaseModalComponent extends BaseComponent {

    public BaseModalComponent(WebDriver driver) {
        super(driver);
    }

    protected boolean isVisible(By rootLocator) {
        try {
            WebElement root = driver.findElement(rootLocator);
            return root.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isClosed(By rootLocator) {
        try {
            WebElement root = driver.findElement(rootLocator);
            return !root.isDisplayed();
        } catch (Exception e) {
            return true; // если элемент не найден — считаем, что закрыт
        }
    }

    protected void closeByClickOutside() {
        // Кликаем в область вне модального окна
        click(By.cssSelector("body")); // или другой безопасный селектор вне модалки
    }

    protected void waitUntilVisible(By rootLocator) {
        WaitUtils.waitUntilVisible(driver, rootLocator);
    }

    protected void waitUntilClosed(By rootLocator) {
        WaitUtils.waitUntilInvisible(driver, rootLocator);
    }

}