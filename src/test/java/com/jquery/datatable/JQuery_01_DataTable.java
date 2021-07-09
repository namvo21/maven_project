
package com.jquery.datatable;

import org.testng.annotations.Test;
import commons.AbstractTest;
import pageObjects.jquery.DataTablePageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class JQuery_01_DataTable extends AbstractTest{
	WebDriver driver;
	DataTablePageObject dataTablePage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String urlValue) {
		driver = getBrowserDriver(browserName, urlValue);
		
		dataTablePage = new DataTablePageObject(driver);
	}

	public void TC_01_Input_To_Column_By_Name(){
		dataTablePage.inputToColumnByName("Country", "Argentina");
		Assert.assertTrue(dataTablePage.isOnlyOneRowDisplayed("Argentina"));
		
		dataTablePage.refresh(driver);
		
		dataTablePage.inputToColumnByName("Total", "1504");
		Assert.assertTrue(dataTablePage.isOnlyOneRowDisplayed("1504"));
		
	}
	
	public void TC_02_Edit_Delete_Icon_By_CountryName(){
		dataTablePage.refresh(driver);
		
		dataTablePage.clickToDynamicIconByCountryName("Argentina","remove");
		dataTablePage.clickToDynamicIconByCountryName("Angola", "remove");
		dataTablePage.clickToDynamicIconByCountryName("Afghanistan", "remove");
		
		dataTablePage.clickToDynamicIconByCountryName("Albania","edit");
	}
	
	@Test
	public void TC_03_Paging_By_Page_Index(){
		dataTablePage.navigateToPageNumberByIndex("10");
		Assert.assertTrue(dataTablePage.isPageActived("10"));
		
		dataTablePage.navigateToPageNumberByIndex("5");
		Assert.assertTrue(dataTablePage.isPageActived("5"));
	}
	
	@Test
	public void TC_04_Dynamic_Row() {
		dataTablePage.openUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		
		dataTablePage.inputToDynamicTextboxAtRowNumber("Company", "2", "Samsung");
		dataTablePage.inputToDynamicTextboxAtRowNumber("Order Placed", "1", "Ho Chi Minh");
		dataTablePage.inputToDynamicTextboxAtRowNumber("Contact Person", "3", "Nguyen Van Troi");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
