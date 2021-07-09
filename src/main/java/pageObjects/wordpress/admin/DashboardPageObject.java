package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.wordpress.admin.DashboardPageUI;

public class DashboardPageObject extends AbstractPage{

	WebDriver driver;
	public DashboardPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
	
	public boolean isHeaderTextDisplayed() {
		waitForElementVisible(driver, DashboardPageUI.HEADER_TEXT);
		return isElementDisplayed(driver, DashboardPageUI.HEADER_TEXT);
	}

	public void clickToScreenOption() {
		waitForElementVisible(driver, DashboardPageUI.SCREEN_OPTION_BUTTON);
		clickToElement(driver, DashboardPageUI.SCREEN_OPTION_BUTTON);
		sleepInSeconds(2);
	}

	public boolean isActivityCheckboxDisplayed() {
		return isElementDisplayed(driver, DashboardPageUI.ACTIVITY_CHECKBOX);
	}

	public boolean isAllPostsSubMenuDisplayed() {
		return isElementDisplayed(driver, DashboardPageUI.ALL_POSTS_SUB_MENU);
	}

	public boolean isPlansMenuDisplayed() {
		return isElementDisplayed(driver, DashboardPageUI.PLANS_LINK);
	}

	public boolean isPlansMenuUndisplayed() {
		return isElementUndisplayed(driver, DashboardPageUI.PLANS_LINK);
	}

}
