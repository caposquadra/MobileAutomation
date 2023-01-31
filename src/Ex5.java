import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
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

public class Ex5 {


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

    @Test
    public void testAddTwoArticlesToReadingListAndRemoveOneOfThem() {

        String search_query_1 = "Java";
        String search_query_2 = "Selenium";
        String reading_list_name = "ReadingList";
        String article_1_subtitle = "oriented programming language";
        String article_2_subtitle = "framework for web applications";
        String article_1_title = "Java (programming language)";
        String article_2_title = "Selenium (software)";

        //Adding the first article to the reading list

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Couldn't find Search input",
                10
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                search_query_1,
                "Couldn't find Search field to input the query",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '"+article_1_subtitle+"')]"),
                "Couldn't find + '"+article_1_subtitle+"' + topic searching by search_line",
                10
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Couldn't find Article title for + '"+article_1_subtitle+"' + article",
                10
        );

        By myFirstArticleTitleSelector = By.id("org.wikipedia:id/view_page_title_text");
        WebElement myFirstArticleTitle = driver.findElement(myFirstArticleTitleSelector);
        String first_article_title = myFirstArticleTitle.getText();
        System.out.println("The first article has article title: " + first_article_title);
        Assert.assertEquals("First article title is not correct",article_1_title, first_article_title);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Couldn't find button to open article option",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']"),
                "Couldn't find button to add the article to reading list",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Couldn't find 'Got it' button",
                10
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Couldn't find input to set name for + '"+article_1_subtitle+"' + article",
                10
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                reading_list_name,
                "Couldn't put text in the folder name",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@text='OK']"),
                "Couldn't press OK button",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Couldn't close article, there is no X button",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView"),
                "Couldn't find button to add an article to My lists",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/item_title"),
                "Couldn't press OK button",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, '"+article_1_subtitle+"')]"),
                "Couldn't find the first article",
                10
        );

        //Adding the second article to the reading list

        waitForElementAndClick(
                By.id("org.wikipedia:id/menu_page_search"),
                "Couldn't press OK button",
                10
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                search_query_2,
                "Couldn't find Search field to input the query",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text, '"+article_2_subtitle+"')]"),
                "Couldn't find + '"+article_2_subtitle+"' + topic searching by search_line",
                10
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Couldn't find Article title for + '"+article_2_subtitle+"' + article",
                10
        );

        By mySecondArticleTitleSelector = By.id("org.wikipedia:id/view_page_title_text");
        WebElement mySecondArticleTitle = driver.findElement(mySecondArticleTitleSelector);
        String second_article_title = mySecondArticleTitle.getText();
        System.out.println("The second article has article title: " + second_article_title);

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Couldn't find button to open article option for + '"+article_2_subtitle+"' + article",
                10
        );

        waitForElementAndClick(
                By.xpath("//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']"),
                "Couldn't find button to add the article to reading list for + '"+article_2_subtitle+"' + article",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, '"+reading_list_name+"')]"),
                "Couldn't find '"+reading_list_name+"' + reading list",
                10
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/snackbar_action"),
                "Couldn't find View List element",
                10
        );

        //Removing the first article from the reading list and check if it has been done correctly

        swipeElementToLeft(
                By.xpath("//*[@text='"+first_article_title+"']"),
                "cannot find saved first article in the reading list"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='"+first_article_title+"']"),
                "cannot delete saved article from the reading list",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, '"+second_article_title+"')]"),
                "Couldn't find the second article in the reading list",
                10
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Couldn't find the second article title",
                10
        );

        By myNotRemovedArticleTitleSelector = By.id("org.wikipedia:id/view_page_title_text");
        WebElement myNotRemovedArticleTitle = driver.findElement(myNotRemovedArticleTitleSelector);
        String my_not_removed_article_title = myNotRemovedArticleTitle.getText();
        Assert.assertEquals("Second article title is not correct",article_2_title, my_not_removed_article_title);
    }

    private WebElement waitForElementPresent (By by, String error_message,long timeOutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

        private WebElement waitForElementPresent(By by, String error_message) {
            return waitForElementPresent(by, error_message, 5);
        }

        private WebElement waitForElementAndClick(By by, String error_message, long timeOutInSeconds) {
            WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
            element.click();
            return element;
        }

        private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeOutInSeconds) {
            WebElement element = waitForElementPresent(by, error_message, timeOutInSeconds);
            element.sendKeys(value);
            return element;
        }

        private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            wait.withMessage(error_message + "\n");
            return wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(by)
            );
        }

        private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
            WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
            element.clear();
            return element;
        }

        protected void swipeElementToLeft(By by, String error_message)

        { WebElement element = waitForElementPresent(
                by,
                error_message,
                10);

            int left_x = element.getLocation().getX();
            int right_x = left_x + element.getSize().getWidth();
            int upper_y = element.getLocation().getY();
            int lower_y = upper_y + element.getSize().getHeight();
            int middle_y = (upper_y + lower_y) / 2;

            TouchAction action = new TouchAction(driver);
            action
                    .press(right_x, middle_y)
                    .waitAction(150)
                    .moveTo(left_x, middle_y)
                    .release()
                    .perform();
        }
}
