
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

public class Login_10_Upload_Multiple_Files extends AbstractTest{
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
	public void TC_01_Upload_Media(){
		mediaPage = dashboardPage.clickToMediaMenu(driver);
		
		mediaPage.UploadMultipleFiles(driver, bullSkull, cactus, decor);
		
		Assert.assertTrue(mediaPage.areFileUploadedDisplayed(driver, bullSkull, cactus, decor));
	}
	

	@AfterClass
	public void afterClass() {
		//closeBrowser();
	}
	
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	PostsPageObject postsPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;

}
