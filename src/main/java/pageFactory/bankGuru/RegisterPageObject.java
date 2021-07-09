package pageFactory.bankGuru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pageObjects.bankGuru.LoginPageObject;
import pageObjects.bankGuru.PageGeneratorManager;

public class RegisterPageObject extends AbstractPage{
	WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.NAME, using = "emailid")
	private WebElement emailIdTextbox;
	
	@FindBy(how = How.NAME, using = "btnLogin")
	private WebElement submitButton;
	
	@FindBy(how = How.XPATH, using = "//td[text()='User ID :']/following-sibling::td")
	private WebElement userIdText;
	
	@FindBy(how = How.XPATH, using = "//td[text()='Password :']//following-sibling::td")
	private WebElement passwordText;

	public void inputToEmailTextBox(String email) {
		waitForElementVisible(driver, emailIdTextbox);
		sendkeyToElement(driver, emailIdTextbox, email);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, submitButton);
		clickToElement(driver, submitButton);
	}

	public String getUserIDText() {
		waitForElementVisible(driver, userIdText);
		return getElementText(driver, userIdText);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, passwordText);
		return getElementText(driver, passwordText);
	}

	public LoginPageObject openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);	
		return PageGeneratorManager.getLoginPage(driver);
	}
}
