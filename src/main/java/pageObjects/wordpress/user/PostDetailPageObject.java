package pageObjects.wordpress.user;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.user.PostDetailPageUI;

public class PostDetailPageObject extends AbstractPage{

	WebDriver driver;
	public PostDetailPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	public boolean isCategoryNameDisplayed(WebDriver driver, String postCategoryCheckbox) {
		return isElementDisplayed(driver, PostDetailPageUI.POST_CATEGORY, postCategoryCheckbox);
	}
	public boolean isTitleNameDisplayed(WebDriver driver, String postTitle) {
		return isElementDisplayed(driver, PostDetailPageUI.POST_TITLE, postTitle);
	}
	public boolean isImageNameDisplayed(WebDriver driver, String imageName) {
		String[] files = imageName.split("\\.");
		String fileName = files[0].toLowerCase();
		waitForElementVisible(driver, PostDetailPageUI.POST_IMAGE, fileName);
		return isElementDisplayed(driver, PostDetailPageUI.POST_IMAGE, fileName);
	}
	public boolean isContentNameDisplayed(WebDriver driver, String postContent) {
		waitForElementVisible(driver, PostDetailPageUI.POST_CONTENT, postContent);
		return isElementDisplayed(driver, PostDetailPageUI.POST_CONTENT, postContent);
	}
	public boolean isDateCreatedDisplayed(WebDriver driver, String postDateCreated) {
		waitForElementVisible(driver, PostDetailPageUI.POST_DATE_CREATED, postDateCreated);
		return isElementDisplayed(driver, PostDetailPageUI.POST_DATE_CREATED, postDateCreated);
	}
	public boolean isAuthorDisplayed(WebDriver driver, String authorName) {
		waitForElementVisible(driver, PostDetailPageUI.POST_AUTHOR, authorName);
		return isElementDisplayed(driver, PostDetailPageUI.POST_AUTHOR, authorName);
	}	
}
