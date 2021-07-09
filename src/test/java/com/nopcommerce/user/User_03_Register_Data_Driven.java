package com.nopcommerce.user;

import org.testng.annotations.Test;
import com.nopcommerce.data.EndUserJson;
import commons.AbstractTest;
import commons.GlobalConstants;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;

public class User_03_Register_Data_Driven extends AbstractTest {
	WebDriver driver;
	EndUserJson data;
	String firstName, lastName, email, companyName, password;
	

	@Parameters({ "browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		
		data = EndUserJson.getEndUserData(GlobalConstants.ROOT_FOLDER + "/testdata/com/nopcommerce/data/EndUser.json");
		
		firstName = data.getFirstName();
		lastName = data.getLastName();
		email = data.getEmail() + randomNumber() + "@hotmail.com";
		companyName = data.getCompanyName();
		password = data.getPassword();
		
	}

	@Test
	public void User_01_Register_To_System() {
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();;
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();
	}
	
	@Test
	public void User_02_Verify_Account() {
		driver.findElement(By.className("ico-account")).click();;
		
		verifyEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		verifyEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		verifyEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		verifyEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
	}

	@AfterClass
	public void afterClass() {
		closeBrowserAndDriver(driver);
	}
}
