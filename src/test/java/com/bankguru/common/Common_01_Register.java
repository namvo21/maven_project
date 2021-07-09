package com.bankguru.common;

import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.RegisterPageObject;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;



public class Common_01_Register extends AbstractTest{
	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;

	public static String userIDValue, passwordValue;

	@Parameters({"browser"})
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANKGURU_URL);
		loginPage = new LoginPageObject(driver);
		loginPage.clickToHereLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.inputToEmailTextBox("cristiano" + randomNumber()  + "@gmail.com");
		registerPage.clickToSubmitButton();
		userIDValue = registerPage.getUserIDText();
		passwordValue = registerPage.getPasswordText();
		
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
