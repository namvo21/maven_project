
package com.wordpress.login;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;

public class Login_12_Assert_Verify extends AbstractTest{
	WebDriver driver;
	String bullSkull = "BullSkull.png";
	String cactus = "Cactus.png";
	String decor = "Decor.png";

	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		loginPage = PageGeneratorManager.getLoginAdminPage(driver);
		
		loginPage.inputToEmailTextbox(GlobalConstants.USER_NAME);

		loginPage.clickToContinueOrLoginButton();

		loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);

		dashboardPage = loginPage.clickToContinueOrLoginButton();

		verifyTrue(dashboardPage.isHeaderTextDisplayed());
	}

	
	@Test
	public void TC_01_Element_Undisplayed_In_DOM(){
		dashboardPage.clickToScreenOption();
		
		verifyTrue(dashboardPage.isActivityCheckboxDisplayed());
		
		dashboardPage.clickToScreenOption();
		
		verifyFalse(dashboardPage.isActivityCheckboxDisplayed());
		
		verifyFalse(dashboardPage.isAllPostsSubMenuDisplayed());
	}
	
	@Test
	public void TC_02_Element_Undisplayed_Without_In_DOM(){
		verifyTrue(dashboardPage.isPlansMenuUndisplayed());
	}
	

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}
	
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	PostsPageObject postsPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;

}
