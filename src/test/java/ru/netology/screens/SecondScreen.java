package ru.netology.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class SecondScreen {

    private final AndroidDriver driver;

    @AndroidFindBy(id = "ru.netology.testing.uiautomator:id/text")
    public WebElement displayedText;

    public SecondScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public String getDisplayedText() {
        return displayedText.getText();
    }
}
