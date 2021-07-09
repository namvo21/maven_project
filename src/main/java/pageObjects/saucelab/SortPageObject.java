package pageObjects.saucelab;

import org.openqa.selenium.WebDriver;
import commons.AbstractPage;
import pageUI.saucelab.SortPageUI;

public class SortPageObject extends AbstractPage{
	WebDriver driver;
	
	public SortPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInSortDropDown(String itemValue) {
		waitForElementClickable(driver, SortPageUI.SORT_DROP_DOWN);
		selectValueInDropDown(driver, SortPageUI.SORT_DROP_DOWN, itemValue);
	}

	public boolean isNameSortAscending() {
		waitForElementsVisible(driver, SortPageUI.PRODUCT_NAME);		
		return isDataSortedAscending(driver, SortPageUI.PRODUCT_NAME);
	}

	public boolean isNameSortDescending() {
		waitForElementsVisible(driver, SortPageUI.PRODUCT_NAME);		
		return isDataSortedDescending(driver, SortPageUI.PRODUCT_NAME);
	}

	public boolean isPriceSortAscending() {
		waitForElementsVisible(driver, SortPageUI.PRODUCT_PRICE);		
		return isPriceSortedAscending(driver, SortPageUI.PRODUCT_PRICE);
	}

	public boolean isPriceSortDescending() {
		waitForElementsVisible(driver, SortPageUI.PRODUCT_PRICE);		
		return isPriceSortedDescending(driver, SortPageUI.PRODUCT_PRICE);
	}
}
