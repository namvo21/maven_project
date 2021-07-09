
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

public class Login_09_Dynamic_Locator_Rest_Param extends AbstractTest{
	WebDriver driver;

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
	public void TC_01_Less_Page(){
		postsPage = dashboardPage.clickToPostsMenu(driver);
		
		pagesPage = (PagesPageObject) postsPage.openMenuPageByPageName(driver, "Pages");
		
		mediaPage = pagesPage.clickToMediaMenu(driver);
		
		postsPage = (PostsPageObject) mediaPage.openMenuPageByPageName(driver, "Posts");
		
		pagesPage = (PagesPageObject) postsPage.openMenuPageByPageName(driver, "Pages");
		
		postsPage = (PostsPageObject) pagesPage.openMenuPageByPageName(driver, "Posts");
		
		mediaPage = postsPage.clickToMediaMenu(driver);
	}
	
	@Test
	public void TC_02_More_Page(){
		mediaPage.openMenuPageByName(driver, "Posts");
		postsPage = PageGeneratorManager.getPostsAdminPage(driver);
		
		postsPage.openMenuPageByName(driver, "Pages");
		pagesPage = PageGeneratorManager.getPagesAdminPage(driver);
		
		pagesPage.openMenuPageByName(driver, "Posts");
		postsPage = PageGeneratorManager.getPostsAdminPage(driver);
		
		mediaPage = postsPage.clickToMediaMenu(driver);
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
