package com.bankguru.login;

import org.testng.annotations.Test;

import commons.AbstractTest;
import commons.GlobalConstants;
import pageObjects.bankGuru.EditCustomerPageObject;
import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.NewCustomerPageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class Login_03_Dymanic_Page_Element extends AbstractTest {
	WebDriver driver;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	EditCustomerPageObject editCustomerPage;

	String userIDValue, passwordValue, loginPageUrl, newCustomerID;
	String customerName, gender, birthDay, address, city, state, pinNo, mobileNum, email;
	String editAddress, editCity, editState, editPinNo, editMobileNum, editEmail;

	@Parameters({ "browser" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, GlobalConstants.BANKGURU_URL);

		customerName = "Selenium Online";
		gender = "male";
		birthDay = "2000-01-10";
		address = "123 Address";
		city = "Ho Chi Minh";
		state = "Thu Duc";
		pinNo = "123456";
		mobileNum = "0123456987";
		email = "cristiano" + randomNumber() + "@gmail.com";

		editAddress = "234 Edit Address";
		editCity = "Edit Ho Chi Minh";
		editState = "Edit Thu Duc";
		editPinNo = "645321";
		editMobileNum = "0987654321";
		editEmail = "ryan" + randomNumber() + "@gmail.com";

		loginPage = PageGeneratorManager.getLoginPage(driver);
		loginPageUrl = loginPage.getLoginPageUrl();
	}

	@Test
	public void TC_01_RegisterToSystem() {
		loginPage.clickToDynamicLink(driver, "here");
		registerPage = PageGeneratorManager.getRegisterPage(driver);

		registerPage.inputToDynamicTextbox(driver, "emailid", email);
		registerPage.clickToDynamicButton(driver, "Submit");

		userIDValue = registerPage.getDynamicValueByColumnName(driver, "User ID");
		passwordValue = registerPage.getDynamicValueByColumnName(driver, "Password");

		loginPage = registerPage.openLoginPage(loginPageUrl);
	}

	@Test
	public void TC_02_LoginToSystem() {
		loginPage.inputToDynamicTextbox(driver, "uid", userIDValue);
		loginPage.inputToDynamicTextbox(driver, "password", passwordValue);
		loginPage.clickToDynamicButton(driver, "LOGIN");
		homePage = PageGeneratorManager.getHomePage(driver);
		verifyTrue(homePage.isWelcomeMessageDisplayed());
	}

	@Test
	public void TC_03_NewCustomer() {
		homePage.clickToDynamicLink(driver, "New Customer");
		newCustomerPage = PageGeneratorManager.getNewCustomerPage(driver);

		newCustomerPage.inputToDynamicTextbox(driver, "name", customerName);
		newCustomerPage.clickToDynamicRadioButton(driver, "m");
		newCustomerPage.inputToDynamicTextbox(driver, "dob", birthDay);
		newCustomerPage.inputToDynamicTextArea(driver, "addr", address);
		newCustomerPage.inputToDynamicTextbox(driver, "city", city);
		newCustomerPage.inputToDynamicTextbox(driver, "state", state);
		newCustomerPage.inputToDynamicTextbox(driver, "pinno", pinNo);
		newCustomerPage.inputToDynamicTextbox(driver, "telephoneno", mobileNum);
		newCustomerPage.inputToDynamicTextbox(driver, "emailid", email);
		newCustomerPage.inputToDynamicTextbox(driver, "password", passwordValue);
		newCustomerPage.clickToDynamicButton(driver, "Submit");

		verifyTrue(newCustomerPage.isDynamicMessageDisplayed(driver, "Customer Registered Successfully!!!"));

		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Customer Name"), customerName);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Gender"), gender);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Birthdate"), birthDay);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Address"), address);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "City"), city);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "State"), state);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Pin"), pinNo);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Mobile No."), mobileNum);
		verifyEquals(newCustomerPage.getDynamicValueByColumnName(driver, "Email"), email);

		newCustomerID = newCustomerPage.getDynamicValueByColumnName(driver, "Customer ID");
	}

	@Test
	public void TC_04_EditCustomer() {
		newCustomerPage.clickToDynamicLink(driver, "Edit Customer");
		editCustomerPage = PageGeneratorManager.getEditCustomerPage(driver);

		editCustomerPage.inputToDynamicTextbox(driver, "cusid", newCustomerID);
		editCustomerPage.clickToDynamicButton(driver, "Submit");
		
		
		editCustomerPage.inputToDynamicTextArea(driver, "addr", editAddress);
		editCustomerPage.inputToDynamicTextbox(driver, "city", editCity);
		editCustomerPage.inputToDynamicTextbox(driver, "state", editState);
		editCustomerPage.inputToDynamicTextbox(driver, "pinno", editPinNo);
		editCustomerPage.inputToDynamicTextbox(driver, "telephoneno", editMobileNum);
		editCustomerPage.inputToDynamicTextbox(driver, "emailid", editEmail);
		editCustomerPage.clickToDynamicButton(driver, "Submit");
		verifyTrue(editCustomerPage.isDynamicMessageDisplayed(driver, "Customer details updated Successfully!!!"));
		
		
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Customer Name"), customerName);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Gender"), gender);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Birthdate"), birthDay);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Address"), editAddress);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "City"), editCity);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "State"), editState);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Pin"), editPinNo);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Mobile No."), editMobileNum);
		verifyEquals(editCustomerPage.getDynamicValueByColumnName(driver, "Email"), editEmail);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}

}
