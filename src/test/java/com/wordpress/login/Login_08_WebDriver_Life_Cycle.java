
package com.wordpress.login;

import org.testng.annotations.Test;
import commons.AbstractTest;
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

public class Login_08_WebDriver_Life_Cycle extends AbstractTest{
	WebDriver driver;

	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		loginPage = PageGeneratorManager.getLoginAdminPage(driver);
	}

	@Test
	public void TC_01_LoginToSystem() {

		loginPage.inputToEmailTextbox("automationeditor");

		loginPage.clickToContinueOrLoginButton();

		loginPage.inputToPasswordTextbox("automationfc");

		dashboardPage = loginPage.clickToContinueOrLoginButton();


		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}
	
	@Test
	public void TC_02_NavigateToPage(){
		postsPage = dashboardPage.clickToPostsMenu(driver);
		
		pagesPage = postsPage.clickToPagesMenu(driver);
		
		mediaPage = pagesPage.clickToMediaMenu(driver);
		
		postsPage = mediaPage.clickToPostMenu(driver);
		
		pagesPage = postsPage.clickToPagesMenu(driver);
		
		postsPage = pagesPage.clickToPostMenu(driver);
		
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
