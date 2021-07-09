package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import commons.PageGeneratorManager;
import pageUI.wordpress.admin.PostsPageUI;

public class PostsPageObject extends AbstractPage{

	WebDriver driver;
	public PostsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	
	public NewEditPostsPageObject clickToPostDetailByTitleName(String postTitle) {
		waitForElementClickable(driver, PostsPageUI.POST_TITLE, postTitle);
		clickToElement(driver, PostsPageUI.POST_TITLE, postTitle);
		return PageGeneratorManager.getNewOrEditPostAdminPage(driver);
	}

	public NewEditPostsPageObject clickToAddNewButton() {
		waitForElementClickable(driver, PostsPageUI.ADD_NEW_BUTTON);
		clickToElement(driver, PostsPageUI.ADD_NEW_BUTTON);
		return PageGeneratorManager.getNewOrEditPostAdminPage(driver);
	}

	public void inputToSearchTextbox(String postTitle) {
		waitForElementClickable(driver, PostsPageUI.SEARCH__POST_ICON);
		clickToElement(driver, PostsPageUI.SEARCH__POST_ICON);
		waitForElementVisible(driver, PostsPageUI.SEARCH_POST_TEXTBOX);
		sendkeyToElement(driver, PostsPageUI.SEARCH_POST_TEXTBOX, postTitle);
		sleepInSeconds(3);
	}

	public boolean isSuccessMessageDisplayed(String message) {
		waitForElementVisible(driver, PostsPageUI.MOVE_TO_TRASH_MESSAGE, message);
		return isElementDisplayed(driver, PostsPageUI.MOVE_TO_TRASH_MESSAGE, message);
	}

	public boolean isNoPostFoundMessageDisplayed(String message) {
		waitForElementVisible(driver, PostsPageUI.NOT_FOUND_MESSAGE, message);
		return isElementDisplayed(driver, PostsPageUI.NOT_FOUND_MESSAGE, message);
	}

	public void clickToTrashTab() {
		waitForElementClickable(driver, PostsPageUI.TRASH_LINK);
		clickToElement(driver, PostsPageUI.TRASH_LINK);
	}

	public void clickToToggleMenu() {
		waitForElementClickable(driver, PostsPageUI.TOOGLE_MENU);
		clickToElement(driver, PostsPageUI.TOOGLE_MENU);
	}

	public void clickToDeleteOption() {
		waitForElementClickable(driver, PostsPageUI.DELETE_OPTION);
		clickToElement(driver, PostsPageUI.DELETE_OPTION);
		acceptAlert(driver);
	}	
}
