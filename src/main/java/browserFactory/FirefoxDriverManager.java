package browserFactory;

import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager extends DriverManager{

	@Override
	protected void createDriver() {
		WebDriverManager.firefoxdriver().driverVersion("0.26.0").setup();
		driver = new FirefoxDriver();
		
	}

}
