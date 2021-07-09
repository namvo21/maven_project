package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class MediaPageObject extends AbstractPage{

	WebDriver driver;
	public MediaPageObject(WebDriver mapDriver) {
		driver = mapDriver;
	}
}
