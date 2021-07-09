package com.wordpress.login;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_02_Apply_AbstractPage_Init {
  AbstractPage abstractPage;
  WebDriver driver;
  
  String emailTextbox = "//input[@id='usernameOrEmail']";
  String passwordTextbox = "//input[@id='password']";
  String loginButton = "//div[@class='login__form-action']/button";
  String emailErrorMesssage = "//div[@class='form-input-validation is-error']/span";
  String passwordErrorMesssage = "//div[@class='form-input-validation is-error']/span";
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/browserDriver/geckodriver");
	  driver = new FirefoxDriver();
	  //abstractPage = new AbstractPage();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }	
  
  @BeforeMethod
  public void beforeMethod() {
	  abstractPage.openUrl(driver, "https://automationfc.wordpress.com/wp-admin");
  }
  
  @Test
  public void Validate_01_EmptyEmail() {
	  abstractPage.sendkeyToElement(driver, emailTextbox, "");
	  abstractPage.clickToElement(driver, loginButton);
	  abstractPage.waitForElementVisible(driver, emailErrorMesssage);
	  
	  Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMesssage), "Please enter a username or email address.");
  }
  
  @Test
  public void Validate_02_InvalidEmail() {
	  abstractPage.sendkeyToElement(driver, emailTextbox, "123@123.123");
	  abstractPage.clickToElement(driver, loginButton);
	  abstractPage.waitForElementVisible(driver, emailErrorMesssage);
	  
	  Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMesssage), "Please log in using your WordPress.com username instead of your email address.");
  }
  
  @Test
  public void Validate_03_EmailNotExist() {
	  abstractPage.sendkeyToElement(driver, emailTextbox, "selenium" + randomNumber() + "@gmail.com");
	  abstractPage.clickToElement(driver, loginButton);
	  abstractPage.waitForElementVisible(driver, emailErrorMesssage);
	  
	  Assert.assertEquals(abstractPage.getElementText(driver, emailErrorMesssage), "User does not exist. Would you like to create a new account?");
  }
  
  @Test
  public void Validate_04_EmptyPassword() {
	  abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
	  abstractPage.clickToElement(driver, loginButton);
	  abstractPage.waitForElementVisible(driver, passwordTextbox);
	  abstractPage.sendkeyToElement(driver, passwordTextbox, "");
	  abstractPage.clickToElement(driver, loginButton);
	  abstractPage.waitForElementVisible(driver, passwordErrorMesssage);
	  
	  Assert.assertEquals(abstractPage.getElementText(driver, passwordErrorMesssage), "Don't forget to enter your password.");
  }
  
  @Test
  public void Validate_05_PasswordLessThan6Chars() {
	  abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
	  abstractPage.clickToElement(driver, loginButton);
	  abstractPage.waitForElementVisible(driver, passwordTextbox);
	  abstractPage.sendkeyToElement(driver, passwordTextbox, "123");
	  abstractPage.clickToElement(driver, loginButton);
	  abstractPage.waitForElementVisible(driver, passwordErrorMesssage);
	  
	  Assert.assertEquals(abstractPage.getElementText(driver, passwordErrorMesssage), "Oops, that's not the right password. Please try again!");
  }
  
  @Test
  public void Validate_06_ValidPassword() {  
	  abstractPage.sendkeyToElement(driver, emailTextbox, "automationeditor");
	  abstractPage.clickToElement(driver, loginButton);
	  abstractPage.waitForElementVisible(driver, passwordTextbox);
	  abstractPage.sendkeyToElement(driver, passwordTextbox, "automationfc");
	  abstractPage.clickToElement(driver, loginButton);
	  
	  Assert.assertTrue(abstractPage.isElementDisplayed(driver, "//h1[text()='Dashboard']"));
	  Assert.assertTrue(abstractPage.isElementDisplayed(driver, "//div[@id='dashboard-widgets-wrap']"));
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
