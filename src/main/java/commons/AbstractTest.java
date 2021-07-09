package commons;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class AbstractTest {
	private WebDriver driver;

	protected final Log log;

	protected AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {

		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().driverVersion("0.26.0").setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().arch64().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().arch64().setup();
			driver = new EdgeDriver();
		} else {
			System.out.print("Please choose your browser");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.SHORT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.ADMIN_WORDPRESS_URL);

		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appURL) {

		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().driverVersion("0.26.0").setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().arch64().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().arch64().setup();
			driver = new EdgeDriver();
		} else {
			System.out.print("Please choose your browser");
		}

		driver.manage().timeouts().implicitlyWait(GlobalConstants.SHORT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);

		return driver;
	}

	protected Date getDateTimeNow() {
		Date date = new Date();
		return date;
	}

	protected void closeBrowser() {
		driver.quit();
	}

	protected int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
	}

	protected boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	protected boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	protected boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	protected void closeBrowserAndDriver(WebDriver driver) {
		try {
			// get ra tên của OS và convert qua chữ thường
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			// Khai báo 1 biến command line để thực thi
			String cmd = "";
			if (driver != null) {
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				}
			} else if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driver.toString().toLowerCase().contains("firefox")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill geckodriver";
				} else if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				}
			}

			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();

			log.info("---------- QUIT BROWSER SUCCESS ----------");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	protected String getCurrentDay() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
		if (day < 10) {
			String dayValue = "0" + day;
			return dayValue;
		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime now = new DateTime();
		int month = now.getMonthOfYear();
		if (month < 10) {
			String monthValue = "0" + month;
			return monthValue;
		}
		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime now = new DateTime();
		return String.valueOf(now.getYear());
	}

	protected String getWordPressToday() {
		return getCurrentDay() + "/" + getCurrentMonth() + "/" + getCurrentYear();
	}
}
