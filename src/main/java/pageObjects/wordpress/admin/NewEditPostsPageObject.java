package pageObjects.wordpress.admin;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.admin.AbstractWordPressPageUI;
import pageUI.wordpress.admin.NewOrEditPostPageUI;

public class NewEditPostsPageObject extends AbstractPage{

	WebDriver driver;
	public NewEditPostsPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	public void clickToUpdateButton(String pageName) {
		openWordPressMenuPageByName(driver, pageName);
	}
	
	public void clickToPublishButton(String pageName) {
		openWordPressMenuPageByName(driver, pageName);	
	}
	
	public void clickToPublishButton() {
		waitForElementClickable(driver, NewOrEditPostPageUI.PUBLISH_BUTTON);
		clickToElement(driver, NewOrEditPostPageUI.PUBLISH_BUTTON);
	}
	
	public void clickToMoveToTrashButton(String pageName) {
		switchToFrameOrIframe(driver, NewOrEditPostPageUI.TINY_MCE_IFRAME);
		openWordPressMenuPageByName(driver, pageName);
		sleepInSeconds(5);
	}
	
	public void inputToPostTitleTextbox(String titleValue) {
		switchToFrameOrIframe(driver, NewOrEditPostPageUI.TINY_MCE_IFRAME);
		waitForElementVisible(driver, NewOrEditPostPageUI.TITLE_TEXTBOX);
		sendkeyToElement(driver, NewOrEditPostPageUI.TITLE_TEXTBOX, titleValue);
	}
	
	public void clickToPostContentTextbox() {
		waitForElementClickable(driver, NewOrEditPostPageUI.TINY_MCE_EDITOR);
		clickToElement(driver, NewOrEditPostPageUI.TINY_MCE_EDITOR);
	}
	
	public void inputToPostContentTextbox(String contentValue) {
		waitForElementVisible(driver, NewOrEditPostPageUI.TINY_MCE_TEXTBOX);
		sendkeyToElement(driver, NewOrEditPostPageUI.TINY_MCE_TEXTBOX, contentValue);
	}
	
	public void clickToSetFeatureImageLink(String pageName) {
		openWordPressMenuPageByName(driver, pageName);
	}
	public void clickToFeaturedButton(String pageName) {
		openWordPressMenuPageByName(driver, pageName);
		switchToDefaultContent(driver);
	}
	public void clickToInserImageButton() {
		waitForElementClickable(driver, NewOrEditPostPageUI.INSERT_BUTTON);
		clickToElement(driver, NewOrEditPostPageUI.INSERT_BUTTON);
	}
	
	public boolean isFeaturedImageDisplayed(String imgName) {
		switchToFrameOrIframe(driver, NewOrEditPostPageUI.TINY_MCE_IFRAME);
		String[] files = imgName.split("\\.");
		String fileName = files[0].toLowerCase();
		waitForElementVisible(driver, NewOrEditPostPageUI.FEATURED_IMAGE_THUMBNAIL,fileName);
		return isElementDisplayed(driver, NewOrEditPostPageUI.FEATURED_IMAGE_THUMBNAIL, fileName);
	}
	public boolean isSuccessMessageDisplayedWithValue(String successMessage) {
		waitForElementVisible(driver, AbstractWordPressPageUI.DYNAMIC_MESSAGE_ON_POST, successMessage);
		return isElementDisplayed(driver, AbstractWordPressPageUI.DYNAMIC_MESSAGE_ON_POST, successMessage);
	}
	public void clickToMenuTabs(String pageName) {
		openWordPressMenuPageByName(driver, pageName);
	}
	
	public void selectCategoryCheckbox(String checkboxLabelText) {
		checkToCheckbox(driver, NewOrEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		sleepInSeconds(1);
	}
	
	public void deselectCategoryCheckbox(String checkboxLabelText) {
		uncheckToCheckbox(driver, NewOrEditPostPageUI.CATEGORY_CHECKBOX, checkboxLabelText);
		sleepInSeconds(1);
	}
	
	public void inputToTagTextbox(String tagValue) {
		waitForElementVisible(driver, NewOrEditPostPageUI.TAG_TEXTBOX);
		sendkeyToElement(driver, NewOrEditPostPageUI.TAG_TEXTBOX, tagValue);
		sendKeyboardToElement(driver, NewOrEditPostPageUI.TAG_TEXTBOX, Keys.ENTER);
		sleepInSeconds(2);
	}
	
	public void clickToDeleteTagIconWithTagName() {
		waitForElementVisible(driver, NewOrEditPostPageUI.REMOVE_TAG_BUTTON);
		clickToElementByJS(driver, NewOrEditPostPageUI.REMOVE_TAG_BUTTON);
	}
	
	public void clearPostTitleTextbox() {
		switchToFrameOrIframe(driver, NewOrEditPostPageUI.TINY_MCE_IFRAME);
		waitForElementVisible(driver, NewOrEditPostPageUI.TITLE_TEXTBOX);
		clickToElement(driver, NewOrEditPostPageUI.TITLE_TEXTBOX);
		clearTextonTextarea(driver, NewOrEditPostPageUI.TITLE_TEXTBOX);
		switchToDefaultContent(driver);
	}

    public void clickToMediaLibrary() {
		waitForElementVisible(driver, NewOrEditPostPageUI.MEDIA_BUTTON);
		clickToElementByJS(driver, NewOrEditPostPageUI.MEDIA_BUTTON);
    }
}
