import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class Ex5 extends CoreTestCase {

    @Test
    public void testAddTwoArticlesToReadingListAndRemoveOneOfThem() {

        String search_query_1 = "Java";
        String search_query_2 = "Selenium";
        String name_of_folder = "Learning programming";

        //Adding the first article to the reading list
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_query_1);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_1_title = ArticlePageObject.getArticleTitle();

        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.clickOnTheAddedArticle("oriented programming language");

        //Adding the second article to the reading list

        NavigationUI.clickSearchIcon();

        SearchPageObject.typeSearchLine(search_query_2);
        SearchPageObject.clickByArticleWithSubstring("Testing framework for web applications");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.addArticleToExistList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI.returnToThePreviousScreen();

        NavigationUI.clickMyList();

        //Removing the first article from the reading list and check if it has been done correctly

        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_1_title);
    }
}
