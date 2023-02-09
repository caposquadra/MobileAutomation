package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INPUT = "org.wikipedia:id/search_container",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULTS_AREA = "org.wikipedia:id/search_results_list",

            SEARCH_RESULTS_CONTAINER_XPATH = "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@resource-id='org.wikipedia:id/page_list_item_title']";

    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */
    public void initSearchInput()
    {
        this.waitForElementPresent(By.id(SEARCH_INPUT), "Cannot find Search input after clicking on the element");
        this.waitForElementAndClick(By.id(SEARCH_INPUT), "Cannot find and click search input", 10);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 10);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 10);
    }

    public void clickCancelButton()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 10);
    }

    public void cancelSearch()
    {
        clickCancelButton();
        clickCancelButton();
    }
    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT), search_line,  "Cannot find and type the search line", 30);
    }

    public void waitForSearchResults(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search results with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click ih search results with substring " + substring, 30);
    }

    public void waitForSearchResultsAreaPresent()
    {
        this.waitForElementPresent(By.id(SEARCH_RESULTS_AREA), "Cannot find search results area");
    }

    public void checkNumberOfSearchResults()
    {
        By mySelector = By.xpath(SEARCH_RESULTS_CONTAINER_XPATH);

        List<WebElement> myElements = driver.findElements(mySelector);
        System.out.println("Number of elements:" + myElements.size());
        Assert.assertTrue("Several articles were not found in search results area",
                myElements.size() >= 2);
    }
}
