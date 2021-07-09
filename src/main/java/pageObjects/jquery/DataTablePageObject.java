package pageObjects.jquery;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.jquery.DataTablePageUI;

public class DataTablePageObject extends AbstractPage{
	WebDriver driver;
	
	public DataTablePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToColumnByName(String columnName, String value) {
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_HEADER_COLUMN_TEXT, columnName);
		sendkeyToElement(driver, DataTablePageUI.DYNAMIC_HEADER_COLUMN_TEXT, value, columnName);
		sendKeyboardToElement(driver, DataTablePageUI.DYNAMIC_HEADER_COLUMN_TEXT, Keys.ENTER, columnName);
	}

	public boolean isOnlyOneRowDisplayed(String textValue) {
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_ONLY_ROW_WITH_TEXT, textValue);
		return countElementNumber(driver, DataTablePageUI.DYNAMIC_ONLY_ROW_WITH_TEXT, textValue)==1 &&
				isElementDisplayed(driver, DataTablePageUI.DYNAMIC_ONLY_ROW_WITH_TEXT, textValue);
	}

	public void clickToDynamicIconByCountryName(String countryName, String iconName) {
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_ICON_BY_COUNTRY_NAME, countryName, iconName);
		clickToElement(driver, DataTablePageUI.DYNAMIC_ICON_BY_COUNTRY_NAME, countryName, iconName);
	}

	public void navigateToPageNumberByIndex(String pageIndex) {
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_PAGE_BY_INDEX, pageIndex);
		clickToElement(driver, DataTablePageUI.DYNAMIC_PAGE_BY_INDEX, pageIndex);
	}

	public boolean isPageActived(String pageIndex) {
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_PAGE_ACTIVED_BY_INDEX, pageIndex);
		return isElementDisplayed(driver, DataTablePageUI.DYNAMIC_PAGE_ACTIVED_BY_INDEX,pageIndex);
	}

	public void inputToDynamicTextboxAtRowNumber(String columnName, String rowNumber, String inputValue) {
		waitForElementVisible(driver, DataTablePageUI.DYNAMIC_COLUMN_POSITION_INDEX, columnName);
		int columnPosition = countElementNumber(driver, DataTablePageUI.DYNAMIC_COLUMN_POSITION_INDEX, columnName) + 1;
		System.out.println("Column name = " + columnName + "at position = " + columnPosition);
		
		sendkeyToElement(driver, DataTablePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, inputValue, rowNumber, Integer.toString(columnPosition));
	}
}
