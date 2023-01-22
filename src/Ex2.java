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

public class Ex2 {

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

    // Необходимо написать функцию, которая проверяет наличие ожидаемого текста у элемента.
    // Предлагается назвать ее assertElementHasText. На вход эта функция должна принимать локатор элемент,
    // ожидаемый текст и текст ошибки, который будет написан в случае, если элемент по этому локатору
    // не содержит текст, который мы ожидаем.
    // Также, необходимо написать тест, который проверяет, что поле ввода для поиска статьи содержит
    // текст (в разных версиях приложения это могут быть тексты "Search..." или "Search Wikipedia",
    // правильным вариантом следует считать тот, которые есть сейчас).
    // Очевидно, что тест должен использовать написанный ранее метод.

    @Test
    public void testElementHasText() {

        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']"),
                "Search Wikipedia",
                "Couldn't find 'Search Wikipedia' text in Search field",
                10
        );

    }

    private WebElement waitForElementPresent(By xpath, String expected_text, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(xpath)
        );
    }

    private WebElement assertElementHasText(By xpath, String expected_text, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(xpath, expected_text, error_message, timeoutInSeconds);
        String search_title = element.getAttribute("text");
        System.out.println(search_title);
        Assert.assertEquals(
                error_message,
                expected_text,
                search_title
        );
        return element;
    }
}



