package pageFactory.wordpress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	
	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}
	
	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public void sendkeyToElement(WebDriver driver, WebElement element, String value) {
		element.clear();
		element.sendKeys(value);
	}
	
	public String getElementText(WebDriver driver, WebElement element) {
		return element.getText().trim();
	}
	
	public void clickToElement(WebDriver driver, WebElement element) {
		element.click();
	}
	
	public boolean isElementDisplayed(WebDriver driver, WebElement element) {
		return element.isDisplayed();
	}
	
	public void waitForElementVisible(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOf(element));
	}
	
	
	public void waitForElementClickable(WebDriver driver, WebElement element) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	private long longTimeout = 30;
	private WebDriverWait explicitWait;
}
