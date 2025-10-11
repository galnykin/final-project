package ru.kupibilet.ui.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.kupibilet.ui.components.base.BaseComponent;

public class PassengerSummaryDropdown extends BaseComponent {

    private static final Logger log = LoggerFactory.getLogger(PassengerSummaryDropdown.class);

    private final By adultPlusButton = By.cssSelector("[data-testid='search-form-pass-minus-button']");
    private final By adultMinusButton = By.cssSelector("[data-testid='search-form-pass-plus-button']");

    private final By addChildPassengerButton = By.cssSelector("[data-testid='search-form-child-add-button']");

    public PassengerSummaryDropdown(WebDriver driver) {
        super(driver);
    }

    public void setAdultCount(int count) {
        int current = getCurrentAdultCount();
        adjustCount(current, count, adultPlusButton, adultMinusButton);
        log.info("Set adult passenger count to {}", count);
    }

    public void addChildPassenger(int age) {
        click(addChildPassengerButton);
        click(childAgeOption(age));
        log.info("Added child passenger with age {}", age);
    }

    private By childAgeOption(int age) {
        if (age < 0 || age > 11) {
            throw new IllegalArgumentException("Child age must be between 0 and 11: " + age);
        }
        return By.cssSelector("[data-testid='search-form-pass-list-age-" + age + "']");
    }

    private void adjustCount(int current, int target, By plusButton, By minusButton) {
        int delta = target - current;
        By button = delta > 0 ? plusButton : minusButton;
        for (int i = 0; i < Math.abs(delta); i++) {
            click(button);
        }
    }

    private int getCurrentAdultCount() {
        // TODO: Implement logic to read current adult count from UI
        return 1; // default assumption
    }

    private int getCurrentChildCount() {
        // TODO: Implement logic to read current child count from UI
        return 0; // default assumption
    }
}
