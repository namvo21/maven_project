package com.wordpress.login;

import org.testng.annotations.Test;

import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.LoginPageObject;

import org.testng.annotations.BeforeClass;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_06_Selenium_Factory{
  WebDriver driver;
  String loginPageUrl;
  LoginPageObject loginPage;
  DashboardPageObject dashboardPage;
  
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/browserDriver/geckodriver");
	  driver = new FirefoxDriver();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.get("https://automationfc.wordpress.com/wp-admin");
	  
	  loginPage = PageGeneratorManager.getLoginAdminPage(driver);
	  
	  loginPageUrl = loginPage.getLoginPageUrl();
  }	
  
  @Test
  public void Validate_01_EmptyEmail() {
	  loginPage.openLoginPage(loginPageUrl);
	  
	  loginPage.inputToEmailTextbox("");
	  
	  loginPage.clickToContinueOrLoginButton();
	   
	  Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(), "Please enter a username or email address."); 
  }
  
  @Test
  public void Validate_02_InvalidEmail() {
	  loginPage.openLoginPage(loginPageUrl);
	  
	  loginPage.inputToEmailTextbox("123@123.123");
	  
	  loginPage.clickToContinueOrLoginButton();
	   
	  Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(), "Please log in using your WordPress.com username instead of your email address."); 
  }
  
  @Test
  public void Validate_03_EmailNotExist() {
	  loginPage.openLoginPage(loginPageUrl);
	  
	  loginPage.inputToEmailTextbox("selenium" + randomNumber() + "@gmail.com");
	  
	  loginPage.clickToContinueOrLoginButton();
	  
	  Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(), "User does not exist. Would you like to create a new account?"); 
  }
  
  @Test
  public void Validate_04_EmptyPassword() {
	  loginPage.openLoginPage(loginPageUrl);
	  
	  loginPage.inputToEmailTextbox("automationeditor");
	  
	  loginPage.clickToContinueOrLoginButton();
	  
	  loginPage.inputToPasswordTextbox("");
	  
	  loginPage.clickToContinueOrLoginButton();
	  
	  Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(), "Don't forget to enter your password."); 
  }
  
  @Test
  public void Validate_05_PasswordLessThan6Chars() {
	  loginPage.openLoginPage(loginPageUrl);
	  
	  loginPage.inputToEmailTextbox("automationeditor");
	  
	  loginPage.clickToContinueOrLoginButton();
	  
	  loginPage.inputToPasswordTextbox("123");
	  
	  loginPage.clickToContinueOrLoginButton();
	  
	  Assert.assertEquals(loginPage.getEmailOrPasswrodErrorMessage(), "Oops, that's not the right password. Please try again!");
  }
  
  @Test
  public void Validate_06_ValidPassword() { 
	  loginPage.openLoginPage(loginPageUrl);
	  
	  loginPage.inputToEmailTextbox("automationeditor");
	  
	  loginPage.clickToContinueOrLoginButton();
	  
	  loginPage.inputToPasswordTextbox("automationfc");
	  
	  dashboardPage = loginPage.clickToContinueOrLoginButton();
	    
	  Assert.assertTrue(dashboardPage.isHeaderTextDisplayed());
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
