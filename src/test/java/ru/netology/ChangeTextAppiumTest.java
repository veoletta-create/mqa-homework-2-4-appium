package ru.netology;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeTextAppiumTest {

    private AndroidDriver driver;
    private WebDriverWait wait;

    private static final String APP_PACKAGE = "ru.netology.testing.uiautomator";
    private static final String APK_PATH = "/Users/veoletta/Desktop/mqa-homeworks/2.2 UI Automator/sample/app/build/outputs/apk/debug/app-debug.apk";

    @BeforeEach
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Pixel 6")
                .setApp(APK_PATH)
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_PACKAGE + ".MainActivity")
                .setAutomationName("UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testEmptyStringInput() {
        WebElement textToBeChanged = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id(APP_PACKAGE + ":id/textToBeChanged")));
        String initialText = textToBeChanged.getText();

        WebElement userInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id(APP_PACKAGE + ":id/userInput")));
        userInput.clear();
        userInput.sendKeys("   ");

        WebElement buttonChange = driver.findElement(By.id(APP_PACKAGE + ":id/buttonChange"));
        buttonChange.click();

        WebElement textAfter = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id(APP_PACKAGE + ":id/textToBeChanged")));
        String result = textAfter.getText();

        assertEquals(initialText, result,
                "Текст не должен меняться при вводе пустой строки");
    }

    @Test
    public void testOpenTextInNewActivity() {
        String textToPass = "NetologySecond";

        WebElement userInput = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id(APP_PACKAGE + ":id/userInput")));
        userInput.clear();
        userInput.sendKeys(textToPass);

        WebElement buttonActivity = driver.findElement(By.id(APP_PACKAGE + ":id/buttonActivity"));
        buttonActivity.click();

        WebElement newActivityText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id(APP_PACKAGE + ":id/text")));

        assertEquals(textToPass, newActivityText.getText(),
                "Текст во второй Activity должен совпадать с введённым");
    }
}