package com.bankguru.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Login_01_PageObject extends AbstractTest{
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

		loginPage = registerPage.openLoginPage(loginPageUrl);
	}

	@Test
	public void TC_02_Login() {
		loginPage.inputToUserIDTextbox(userIDValue);
		loginPage.inputToPasswordTextbox(passwordValue);

		homePage = loginPage.clickToLoginButton();
		verifyTrue(homePage.isWelcomeMessageDisplayed());
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
