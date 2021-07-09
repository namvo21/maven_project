
package com.saucelab.sort;

import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.saucelab.SortPageObject;
import pageUI.saucelab.SortPageUI;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Sort_Asc_Desc extends AbstractTest {
	WebDriver driver;
	SortPageObject sortPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = getBrowserDriver(browserName, urlValue);

		sortPage = new SortPageObject(driver);
	}

	@Test
	public void TC_01_Sort_Name() {
		sortPage.sendkeyToElement(driver, SortPageUI.USER_NAME_TEXTBOX, GlobalConstants.SAUCELAB_USER_NAME);
		sortPage.sendkeyToElement(driver, SortPageUI.PASSWORD_TEXTBOX, GlobalConstants.SAUCELAB_PASSWORD);
		sortPage.clickToElement(driver, SortPageUI.LOGIN_BUTTON);
		
		sortPage.selectItemInSortDropDown("Name (A to Z)");

		verifyTrue(sortPage.isNameSortAscending());

		sortPage.selectItemInSortDropDown("Name (Z to A)");

		verifyTrue(sortPage.isNameSortDescending());
	}

	@Test
	public void TC_02_Sort_Price() {
		sortPage.sendkeyToElement(driver, SortPageUI.USER_NAME_TEXTBOX, GlobalConstants.SAUCELAB_USER_NAME);
		sortPage.sendkeyToElement(driver, SortPageUI.PASSWORD_TEXTBOX, GlobalConstants.SAUCELAB_PASSWORD);
		sortPage.clickToElement(driver, SortPageUI.LOGIN_BUTTON);
		sortPage.selectItemInSortDropDown("Price (low to high)");

		verifyTrue(sortPage.isPriceSortAscending());

		sortPage.selectItemInSortDropDown("Price (high to low)");

		verifyTrue(sortPage.isPriceSortDescending());
	}

	@AfterClass
	public void afterClass() {
		//closeBrowserAndDriver(driver);
	}

}
