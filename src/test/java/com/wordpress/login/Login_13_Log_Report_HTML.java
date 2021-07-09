
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

public class Login_13_Log_Report_HTML extends AbstractTest{
	WebDriver driver;
	String bullSkull = "BullSkull.png";
	String cactus = "Cactus.png";
	String decor = "Decor.png";

	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		log.info("Pre-Condition - Open browser");
		driver = getBrowserDriver(browserName);

		log.info("Pre-condition - STEP 01: Open Login Page");
		loginPage = PageGeneratorManager.getLoginAdminPage(driver);
		
		log.info("Pre-condition - STEP 02: Input to 'Email' textbox");
		loginPage.inputToEmailTextbox(GlobalConstants.USER_NAME);

		log.info("Pre-condition - STEP 03: Click to 'Continue' button");
		loginPage.clickToContinueOrLoginButton();

		log.info("Pre-condition - STEP 04: Input to 'Password' textbox");
		loginPage.inputToPasswordTextbox(GlobalConstants.PASSWORD);

		log.info("Pre-condition - STEP 05: Click to 'Login' button");
		dashboardPage = loginPage.clickToContinueOrLoginButton();

		log.info("Pre-condition - STEP 06: Verify 'Header' text is displayed");
		verifyTrue(dashboardPage.isHeaderTextDisplayed());
	}

	
	@Test
	public void TC_01_New_Customer(){
		log.info("New Customer - STEP 01: Click to 'Screen' option");
		dashboardPage.clickToScreenOption();
		
		log.info("New Customer - STEP 02: Verify 'Activity' checkbox is displayed");
		verifyTrue(dashboardPage.isActivityCheckboxDisplayed());
		
		log.info("New Customer - STEP 03: Click to 'Screen' option");
		dashboardPage.clickToScreenOption();
		
		log.info("New Customer - STEP 04: Verify 'Activity' checkbox is not displayed");
		verifyFalse(dashboardPage.isActivityCheckboxDisplayed());
		
		log.info("New Customer - STEP 05: Verify 'All Posts' submenu is not displayed");
		verifyFalse(dashboardPage.isAllPostsSubMenuDisplayed());
	}
	
	public void TC_02_Edit_Customer(){
		log.info("Edit Customer - STEP 01: Verify 'Plans' menu is displayed");
		verifyTrue(dashboardPage.isPlansMenuUndisplayed());
	}
	

	@AfterClass
	public void afterClass() {
		log.info("Post-Condition - STEP 01: Close browser");
		closeBrowser();
	}
	
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;
	PostsPageObject postsPage;
	MediaPageObject mediaPage;
	PagesPageObject pagesPage;

}
