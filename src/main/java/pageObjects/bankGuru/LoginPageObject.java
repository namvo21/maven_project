package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankGuru.LoginPageUI;

public class LoginPageObject extends AbstractPage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public String getLoginPageUrl() {
		return getCurrentUrl(driver);
	}

	public RegisterPageObject clickToHereLink() {
		waitForElementClickable(driver, LoginPageUI.HERE_LINK);
		clickToElement(driver, LoginPageUI.HERE_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public void inputToUserIDTextbox(String userIDValue) {
		waitForElementVisible(driver, LoginPageUI.USER_ID_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USER_ID_TEXTBOX, userIDValue);
	}

	public void inputToPasswordTextbox(String passwordValue) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getHomePage(driver);
	}

}
