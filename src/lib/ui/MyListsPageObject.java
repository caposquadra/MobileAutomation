package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public static final String
        FOLDER_BY_NAME_TPL = "//*[@text='{FOLDER_NAME}']",
        ARTICLE_IN_MY_LIST_TPL = "//*[contains(@text, '{ARTICLE_SUBTITLE}')]";

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getArticleXpathBySubtitle(String article_subtitle)
    {
        return ARTICLE_IN_MY_LIST_TPL.replace("{ARTICLE_SUBTITLE}", article_subtitle);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
        By.xpath(folder_name_xpath),
        "Cannot find folder by name " + name_of_folder,
        10
        );
    }

    public void clickOnTheAddedArticle(String article_subtitle)
    {
        String article_subtitle_xpath = getArticleXpathBySubtitle(article_subtitle);
        this.waitForElementAndClick(
                By.xpath(article_subtitle_xpath),
                "Cannot find an article by xpath " + article_subtitle,
                10
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getArticleXpathBySubtitle(article_title);
        this.waitForElementPresent(
                By.xpath(article_xpath),
                "Cannot find saved article by title " +article_title,
                10
        );
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getArticleXpathBySubtitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_xpath),
                "Saved article still present with title " +article_title,
                10
        );
    }

    public void swipeByArticleToDelete(String article_title) {
        String article_xpath = getArticleXpathBySubtitle(article_title);

        this.waitForArticleToAppearByTitle(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Couldn't find article"
        );

        this.waitForArticleToDisappearByTitle(article_title);
    }

}
