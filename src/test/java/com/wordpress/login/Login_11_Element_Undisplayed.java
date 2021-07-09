
package com.wordpress.login;

import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_11_Element_Undisplayed extends AbstractTest{
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

		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}

	
	@Test
	public void TC_01_Element_Undisplayed_In_DOM(){
		dashboardPage.clickToScreenOption();
		
		System.out.println("Start to check activity checkbox displayed = " + getDateTimeNow());
		Assert.assertTrue(dashboardPage.isActivityCheckboxDisplayed());
		System.out.println("End to check activity checkbox displayed = " + getDateTimeNow());
		
		dashboardPage.clickToScreenOption();
		
		System.out.println("Start to check activity checkbox not displayed = " + getDateTimeNow());
		Assert.assertFalse(dashboardPage.isActivityCheckboxDisplayed());
		System.out.println("End to check activity checkbox not displayed = " + getDateTimeNow());
		
		System.out.println("Start to check All Posts Sub Menu not displayed = " + getDateTimeNow());
		Assert.assertFalse(dashboardPage.isAllPostsSubMenuDisplayed());
		System.out.println("End to check All Posts Sub Menu not displayed = " + getDateTimeNow());
	}
	
	@Test
	public void TC_02_Element_Undisplayed_Without_In_DOM(){
		System.out.println("Start to check Plans Menu not displayed = " + getDateTimeNow());
		Assert.assertTrue(dashboardPage.isPlansMenuUndisplayed());
		System.out.println("End to check Plans Menu not displayed = " + getDateTimeNow());
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
