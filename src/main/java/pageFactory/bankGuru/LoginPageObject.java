package pageFactory.bankGuru;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import pageObjects.bankGuru.HomePageObject;
import pageObjects.bankGuru.PageGeneratorManager;
import pageObjects.bankGuru.RegisterPageObject;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(how = How.XPATH, using = "//a[text()='here']")
	private WebElement hereLink;

	@FindBy(how = How.NAME, using = "uid")
	private WebElement userIDTextbox;

	@FindBy(how = How.NAME, using = "password")
	private WebElement passwordTextbox;

	@FindBy(how = How.NAME, using = "btnLogin")
	private WebElement loginButton;

	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}

	public RegisterPageObject clickToHereLink() {
		waitForElementClickable(driver, hereLink);
		clickToElement(driver, hereLink);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputToUserIDTextbox(String userIDValue) {
		waitForElementVisible(driver, userIDTextbox);
		sendkeyToElement(driver, userIDTextbox, userIDValue);
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, passwordValue);
	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
		return PageGeneratorManager.getHomePage(driver);
	}
}
