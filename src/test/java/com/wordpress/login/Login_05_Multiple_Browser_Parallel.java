package com.wordpress.login;

import org.testng.annotations.Test;
import commons.AbstractTest;
import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_05_Multiple_Browser_Parallel extends AbstractTest{
	WebDriver driver;

	@Parameters({"browser"})
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		loginPage = PageGeneratorManager.getLoginAdminPage(driver);

		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@BeforeMethod
	public void beforeMethod() {
		loginPage.openLoginPage(loginPageUrl);
	}

	@Test
	public void Validate_01_EmptyEmail() {

		loginPage.inputToEmailTextbox("");

		loginPage.clickToContinueOrLoginButton();

		Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(), "Please enter a username or email address.");
	}

	@Test
	public void Validate_02_InvalidEmail() {

		loginPage.inputToEmailTextbox("123@123.123");

		loginPage.clickToContinueOrLoginButton();

		Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(),
				"Please log in using your WordPress.com username instead of your email address.");
	}

	@Test
	public void Validate_03_EmailNotExist() {

		loginPage.inputToEmailTextbox("selenium" + randomNumber() + "@gmail.com");

		loginPage.clickToContinueOrLoginButton();

		Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(),
				"User does not exist. Would you like to create a new account?");
	}

	@Test
	public void Validate_04_EmptyPassword() {

		loginPage.inputToEmailTextbox("automationeditor");

		loginPage.clickToContinueOrLoginButton();

		loginPage.inputToPasswordTextbox("");

		loginPage.clickToContinueOrLoginButton();

		Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(), "Don't forget to enter your password.");
	}

	@Test
	public void Validate_05_PasswordLessThan6Chars() {

		loginPage.inputToEmailTextbox("automationeditor");

		loginPage.clickToContinueOrLoginButton();

		loginPage.inputToPasswordTextbox("123");

		loginPage.clickToContinueOrLoginButton();

		Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(),
				"Oops, that's not the right password. Please try again!");
	}

	@Test
	public void Validate_06_ValidPassword() {

		loginPage.inputToEmailTextbox("automationeditor");

		loginPage.clickToContinueOrLoginButton();

		loginPage.inputToPasswordTextbox("automationfc");

		loginPage.clickToContinueOrLoginButton();

		dashboardPage = new DashboardPageObject(driver);

		Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}
	
	String loginPageUrl;
	LoginPageObject loginPage;
	DashboardPageObject dashboardPage;

}
