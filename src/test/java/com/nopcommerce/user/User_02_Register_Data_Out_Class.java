package com.nopcommerce.user;

import org.testng.annotations.Test;

import com.nopcommerce.data.EndUser;
import commons.AbstractTest;
import commons.DataHelper;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_02_Register_Data_Out_Class extends AbstractTest {
	WebDriver driver;
	DataHelper data;
	String firstName, lastName, email, companyName, password;
	

	@Parameters({ "browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		email = "Automation" + randomNumber() + "@hotmail.com";
				
	}

	@Test
	public void User_01_Register_To_System() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();;
		
		driver.findElement(By.id("FirstName")).sendKeys(EndUser.FIRST_NAME);
		driver.findElement(By.id("LastName")).sendKeys(EndUser.LAST_NAME);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(EndUser.COMPANY_NAME);
		driver.findElement(By.id("Password")).sendKeys(EndUser.PASSWORD);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(EndUser.PASSWORD);
		
		driver.findElement(By.id("register-button")).click();
	}
	
	@Test
	public void User_02_Verify_Account() {
		driver.findElement(By.className("ico-account")).click();;
		
		verifyEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), EndUser.FIRST_NAME);
		verifyEquals(driver.findElement(By.id("LastName")).getAttribute("value"), EndUser.LAST_NAME);
		verifyEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		verifyEquals(driver.findElement(By.id("Company")).getAttribute("value"), EndUser.COMPANY_NAME);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
