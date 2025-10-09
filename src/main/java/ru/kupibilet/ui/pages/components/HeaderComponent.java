package ru.kupibilet.ui.pages.components;

import org.openqa.selenium.WebDriver;
import ru.kupibilet.ui.pages.base.BaseComponent;

import java.time.LocalDate;

public class HeaderComponent extends BaseComponent {

    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public void search(String from, String to, LocalDate date) {
        // реализация
    }
}
