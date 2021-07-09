package com.bankguru.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

public class Login_02_Selenium_PageFactory extends AbstractTest{
	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	String userIDValue, passwordValue, loginPageUrl;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANKGURU_URL);

		loginPage = PageGeneratorManager.getLoginPage(driver);

		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void TC_01_Register() {
		registerPage = loginPage.clickToHereLink();

		registerPage.inputToEmailTextBox("cristiano" + randomNumber() + "@gmail.com");
		registerPage.clickToSubmitButton();

		userIDValue = registerPage.getUserIDText();
		passwordValue = registerPage.getPasswordText();
	}

	@Test
	public void TC_02_Login() {
		loginPage = registerPage.openLoginPage(loginPageUrl);
		
		loginPage.inputToUserIDTextbox(userIDValue);
		loginPage.inputToPasswordTextbox(passwordValue);

		homePage = loginPage.clickToLoginButton();

		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
	}

	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
