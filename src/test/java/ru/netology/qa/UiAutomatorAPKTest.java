package ru.netology.qa;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class UiAutomatorAPKTest {

    private AndroidDriver driver;

    UiAutomatorAPKPage locators;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/wd/hub");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);

        locators = new UiAutomatorAPKPage(driver);
    }

    @Test
    public void checkEmptySymbol() {
        String defText = "Привет, UiAutomator!";
        locators.textToBeChanged.isDisplayed();
        locators.textToBeChanged.click();
        String textNow = locators.textToBeChanged.getText();
        Assertions.assertEquals(textNow, defText);
        locators.userInput.isDisplayed();
        locators.userInput.click();
        locators.userInput.sendKeys(" ");
        locators.buttonChange.isDisplayed();
        locators.buttonChange.click();
        locators.textToBeChanged.isDisplayed();
        String textAfter = locators.textToBeChanged.getText();
        Assertions.assertEquals(textAfter, defText);
    }
    @Test
    public void textInNewActivity() {
        String newText = "Изменение";
        String defText = "Привет, UiAutomator!";
        locators.textToBeChanged.isDisplayed();
        locators.textToBeChanged.click();
        String textNow = locators.textToBeChanged.getText();
        Assertions.assertEquals(textNow, defText);
        locators.userInput.isDisplayed();
        locators.userInput.click();
        locators.userInput.sendKeys(newText);
        locators.buttonChange.isDisplayed();
        locators.buttonChange.click();
        locators.buttonActivity.isDisplayed();
        locators.buttonActivity.click();
        locators.text.isDisplayed();
        textNow = locators.text.getText();
        Assertions.assertEquals(textNow, newText);

    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}