package pageFactory.wordpress;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;


public class DashboardPageObject extends AbstractPage{
	
	WebDriver driver;
	
	public DashboardPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(how = How.XPATH, using = "//h1[text()='Dashboard']")
	WebElement headerText;
	
	
	public boolean isHeaderTextDisplayed() {
		waitForElementVisible(driver, headerText);
		return isElementDisplayed(driver, headerText);
	}
}
