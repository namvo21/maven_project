package pageFactory.wordpress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.PageGeneratorManager;
import pageObjects.wordpress.admin.DashboardPageObject;


public class LoginPageObject extends AbstractPage{

	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//input[@id='usernameOrEmail']")
	WebElement emailTextbox;
	
	@FindBy(how = How.XPATH, using = "//input[@id='password']")
	WebElement passwordTextbox;
	
	@FindBy(how = How.XPATH, using = "//div[@class='login__form-action']/button")
	WebElement continueOrLoginButton;
	
	@FindBy(how = How.XPATH, using = "//div[@class='form-input-validation is-error']/span")
	WebElement emailOrPasswordErrorMessage;
	
	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}
	
	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, email);
	}

	public DashboardPageObject clickToContinueOrLoginButton() {
		waitForElementVisible(driver, continueOrLoginButton);
		clickToElement(driver, continueOrLoginButton);
		return PageGeneratorManager.getDashboardAdminPage(driver);
	}

	public String getEmailOrPasswrodErrorMessage() {
		waitForElementVisible(driver, emailOrPasswordErrorMessage);
		return getElementText(driver, emailOrPasswordErrorMessage);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
	}
}
