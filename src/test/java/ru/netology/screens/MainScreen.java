package ru.netology.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class MainScreen {

    private final AndroidDriver driver;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/textToBeChanged")
    public WebElement textToBeChanged;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/userInput")
    public WebElement userInput;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonChange")
    public WebElement buttonChange;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/buttonActivity")
    public WebElement buttonActivity;

    public MainScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public String getCurrentText() {
        return textToBeChanged.getText();
    }

    public void enterText(String text) {
        userInput.clear();
        userInput.sendKeys(text);
    }

    public void clickChangeButton() {
        buttonChange.click();
    }

    public void clickOpenActivityButton() {
        buttonActivity.click();
    }
}