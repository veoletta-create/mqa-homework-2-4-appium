package ru.netology;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.screens.MainScreen;
import ru.netology.screens.SecondScreen;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangeTextAppiumTest {

    private AndroidDriver driver;

    private static final String APP_PACKAGE = "ru.netology.testing.uiautomator";
    private static final String APP_ACTIVITY = "ru.netology.testing.uiautomator.MainActivity";

    @BeforeEach
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setDeviceName("Pixel 6")
                .setAppPackage(APP_PACKAGE)
                .setAppActivity(APP_ACTIVITY)
                .setAutomationName("UiAutomator2");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testEmptyStringInput() {
        MainScreen mainScreen = new MainScreen(driver);

        String initialText = mainScreen.getCurrentText();

        mainScreen.enterText("   ");
        mainScreen.clickChangeButton();

        String result = mainScreen.getCurrentText();

        assertEquals(initialText, result,
                "Текст не должен меняться при вводе пустой строки");
    }

    @Test
    public void testOpenTextInNewActivity() {
        MainScreen mainScreen = new MainScreen(driver);

        String textToPass = "NetologySecond";

        mainScreen.enterText(textToPass);
        mainScreen.clickOpenActivityButton();

        SecondScreen secondScreen = new SecondScreen(driver);

        assertEquals(textToPass, secondScreen.getDisplayedText(),
                "Текст во второй Activity должен совпадать с введённым");
    }
}
