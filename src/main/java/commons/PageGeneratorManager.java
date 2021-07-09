package commons;
import org.openqa.selenium.WebDriver;

import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.NewEditPostsPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDetailPageObject;
import pageObjects.wordpress.user.SearchResultPageObject;

public class PageGeneratorManager {
	public static LoginPageObject getLoginAdminPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static DashboardPageObject getDashboardAdminPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}
	
	public static PostsPageObject getPostsAdminPage(WebDriver driver) {
		return new PostsPageObject(driver);
	}
	
	public static MediaPageObject getMediaAdminPage(WebDriver driver) {
		return new MediaPageObject(driver);
	}
	
	public static PagesPageObject getPagesAdminPage(WebDriver driver) {
		return new PagesPageObject(driver);
	}
	
	public static NewEditPostsPageObject getNewOrEditPostAdminPage(WebDriver driver) {
		return new NewEditPostsPageObject(driver);
	}
	
	public static HomePageObject getHomeUserPage(WebDriver driver) {
		return new HomePageObject(driver);
	}
	
	public static PostDetailPageObject getPostDetailUserPage(WebDriver driver) {
		return new PostDetailPageObject(driver);
	}
	
	public static SearchResultPageObject getSearchResultUserPage(WebDriver driver) {
		return new SearchResultPageObject(driver);
	}
}
