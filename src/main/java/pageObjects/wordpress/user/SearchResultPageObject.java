package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.SearchResultPageUI;

public class SearchResultPageObject extends AbstractPage{

	WebDriver driver;
	public SearchResultPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}

	public boolean isPostTitleDisplayedOnHeader(WebDriver driver, String postTitle) {
		waitForElementVisible(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, postTitle);
		return isElementDisplayed(driver, SearchResultPageUI.POST_TITLE_ON_HEADER, postTitle);
	}

	public boolean isNoPostFoundMessageDisplayed(String message) {
		waitForElementVisible(driver, SearchResultPageUI.NOT_FOUND_MESSAGE, message);
		return isElementDisplayed(driver, SearchResultPageUI.NOT_FOUND_MESSAGE, message);
	}	
}
