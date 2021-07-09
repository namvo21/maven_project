package com.bankguru.user;

import org.testng.annotations.Test;

import com.bankguru.common.Common_01_Register;

import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class User_02_Search_User extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANKGURU_URL);

		loginPage = new LoginPageObject(driver);
		loginPage.inputToUserIDTextbox(Common_01_Register.userIDValue);
		loginPage.inputToPasswordTextbox(Common_01_Register.passwordValue);
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	}
	
	@Test
	public void TC_01_Search_Name() {
		
	}
	
	@Test
	public void TC_02_Search_Adress() {
		
	}
	
	@Test
	public void TC_03_Search_Phone() {
		
	}
	
	@Test
	public void TC_04_Search_City() {
		
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
