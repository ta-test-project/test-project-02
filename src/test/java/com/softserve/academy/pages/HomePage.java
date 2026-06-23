package com.softserve.academy.pages;

import com.softserve.academy.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final By SIGN_UP_BUTTON = By.cssSelector(".header_sign-up-btn > span");
    private static final By SNACKBAR = By.cssSelector(".mdc-snackbar__label");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public RegistrationModal openRegistrationModal() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(SNACKBAR));
        wait.until(ExpectedConditions.elementToBeClickable(SIGN_UP_BUTTON)).click();
        return new RegistrationModal(driver);
    }
}
