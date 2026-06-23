package com.softserve.academy.pages;

import com.softserve.academy.core.BasePage;
import com.softserve.academy.data.RegistrationData;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationModal extends BasePage {

    private static final By EMAIL_FIELD = By.id("email");
    private static final By FIRST_NAME_FIELD = By.id("firstName");
    private static final By PASSWORD_FIELD = By.id("password");
    private static final By REPEAT_PASSWORD_FIELD = By.id("repeatPassword");
    private static final By SUBMIT_BUTTON = By.cssSelector(".greenStyle");
    private static final By SUCCESS_MESSAGE = By.cssSelector(".mdc-snackbar__label");

    public RegistrationModal(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD));
    }

    public void fillRegistrationForm(RegistrationData data) {
        enterText(EMAIL_FIELD, data.getEmail());
        enterText(FIRST_NAME_FIELD, data.getName());
        enterText(PASSWORD_FIELD, data.getPassword());
        enterText(REPEAT_PASSWORD_FIELD, data.getPassword());
    }

    public void submitRegistration() {
        WebElement btnSubmit = wait.until(ExpectedConditions.elementToBeClickable(SUBMIT_BUTTON));
        if (btnSubmit.isEnabled()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnSubmit);
        }
    }

    public String getSuccessMessageText() {
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_MESSAGE));
        return successMsg.getText();
    }

    private void enterText(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }
}
