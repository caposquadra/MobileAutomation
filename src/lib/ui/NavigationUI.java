package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            MY_LIST_LINK = "//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView",
            SEARCH_ICON = "org.wikipedia:id/menu_page_search",
            RETURN_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList()
    {
        this.waitForElementAndClick(
                By.xpath(MY_LIST_LINK),
                "Couldn't find button to add an article to My lists",
                10
        );
    }

    public void clickSearchIcon()
    {
        this.waitForElementAndClick(
                By.id(SEARCH_ICON),
                "Couldn't find search icon to click on it",
                10
        );
    }

    public void returnToThePreviousScreen() {
        this.waitForElementAndClick(
                By.xpath(RETURN_BUTTON),
                "Couldn't return, there is no the button",
                10
        );
    }
}
