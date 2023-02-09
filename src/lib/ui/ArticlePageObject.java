package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTION_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            ADD_TO_MY_LIST = "//android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            VIEW_LIST = "org.wikipedia:id/snackbar_action",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            ARTICLE_TITLE_ID = "org.wikipedia:id/view_page_title_text";
    public static final String
            FOLDER_BY_EXIST_NAME_TPL = "//*[@text='{FOLDER_NAME}']";
    private static String getExistFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_EXIST_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement() {
            return this.waitForElementPresent(By.id(TITLE), "Cannot find article title",  10);
    }
    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "can't find the end of article",
                20
        );
    }
    public void addArticleToExistList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Couldn't find button to open article option",
                10
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST),
                "Couldn't find button to add the article to reading list",
                10
        );
        String exist_folder_name_xpath = getExistFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(exist_folder_name_xpath),
                "Couldn't find '"+name_of_folder+"' + reading list",
                10
        );
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTION_BUTTON),
                "Couldn't find button to open article option",
                10
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST),
                "Couldn't find button to add the article to reading list",
                10
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Couldn't find 'Got it' button",
                10
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Couldn't find input to set name for an article",
                10
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Couldn't put text in the folder name",
                10
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Couldn't press OK button",
                10
        );
    }
    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Couldn't close article, there is no X button",
                10
        );
    }
    public static void assertElementPresent() {
        WebElement element = driver.findElement(By.id(ARTICLE_TITLE_ID));
        Assert.assertTrue("There is no an article title element on the page", element != null);
    }
}