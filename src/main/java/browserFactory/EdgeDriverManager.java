package browserFactory;

import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EdgeDriverManager extends DriverManager{

	@Override
	protected void createDriver() {
		WebDriverManager.edgedriver().arch64().setup();
		driver = new EdgeDriver();
	}

}
