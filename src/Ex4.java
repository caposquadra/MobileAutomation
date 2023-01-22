import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class Ex4 {

    private AppiumDriver driver;

    @Before
    public void setup() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "8.0.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\dmitr\\OneDrive\\Desktop\\Автоматизация МП\\02 Установка и настройка инструментов\\04. Архив с утилитами\\Tools\\org.wikipedia.apk");

        String spec;
        driver = new AndroidDriver(new URL(spec = "http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    // Написать тест, который делает поиск по какому-то слову. Например, Java. Затем убеждается,
    // что в каждом результате поиска есть это слово.

    @Test
    public void ValidateSearchByJavaWord() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Couldn't find Search field",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Java",
                "Couldn't find Search field",
                5
        );

        WebElement search_area = waitForElementPresent(
                By.id("org.wikipedia:id/search_results_list"),
                "Couldn't Search Results area",
                15
        );
        By mySelector = By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']");

        List<WebElement> myElements = driver.findElements(mySelector);
        System.out.println("Number of elements:" +myElements.size());
        int count = 0;
        for(WebElement e : myElements) {
            System.out.println(e.getText());
            String string = e.getText();
            String substring = "Java";
            if (string.contains(substring))
            {
                count = count+1;
            }
        }
        Assert.assertTrue("There are no 'Java' word in at least one search result",
                count == 3);
    }

    private WebElement waitForElementPresent (By by, String error_message,long timoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent (By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick (By by, String error_message,long timoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys (By by, String value, String error_message,long timoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timoutInSeconds);
        element.sendKeys(value);
        return element;
    }

}

