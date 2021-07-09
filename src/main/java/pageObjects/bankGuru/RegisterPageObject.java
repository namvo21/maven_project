package pageObjects.bankGuru;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;
import pageUI.bankGuru.RegisterPageUI;

public class RegisterPageObject extends AbstractPage{
	WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextBox(String email) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, email);
	}

	public void clickToSubmitButton() {
		waitForElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUserIDText() {
		waitForElementVisible(driver, RegisterPageUI.USER_ID_TEXT);
		return getElementText(driver, RegisterPageUI.USER_ID_TEXT);
	}

	public String getPasswordText() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXT);
		return getElementText(driver, RegisterPageUI.PASSWORD_TEXT);
	}

	public LoginPageObject openLoginPage(String loginPageUrl) {
		openUrl(driver, loginPageUrl);	
		return PageGeneratorManager.getLoginPage(driver);
	}


}
