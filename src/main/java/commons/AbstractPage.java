package commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.wordpress.admin.DashboardPageObject;
import pageObjects.wordpress.admin.MediaPageObject;
import pageObjects.wordpress.admin.PagesPageObject;
import pageObjects.wordpress.admin.PostsPageObject;
import pageObjects.wordpress.user.HomePageObject;
import pageObjects.wordpress.user.PostDetailPageObject;
import pageObjects.wordpress.user.SearchResultPageObject;
import pageUI.bankGuru.AbstractBankPageUI;
import pageUI.wordpress.admin.AbstractWordPressPageUI;
import pageUI.wordpress.admin.NewOrEditPostPageUI;

public abstract class AbstractPage {
	public boolean isPageLoaded(WebDriver driver, String pageUrl) {
		String actualUrl = driver.getCurrentUrl();
		return actualUrl.endsWith(pageUrl);
	}

	public void openUrl(WebDriver driver, String urlValue) {
		driver.get(urlValue);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	public void sendKeyToAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public void waitAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait;
		explicitWait = new WebDriverWait(driver, 15);
		explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void switchToWWindowByID(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);

		for (String Id : allWindows) {
			if (!Id.equals(parentID)) {
				driver.switchTo().window(Id);
				break;
			}
		}
	}

	public void switchToWWindowByTitle(WebDriver driver, String windowTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		System.out.println(allWindows);

		for (String Id : allWindows) {
			driver.switchTo().window(Id);
			String title = driver.getTitle();

			if (title.equals(windowTitle)) {
				break;
			}
		}
	}

	public boolean arAllWindowsCloseWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();

		for (String Id : allWindows) {
			if (!Id.equals(parentID)) {
				driver.switchTo().window(Id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1) {
			return true;
		}
		return false;
	}

	public By byXpath(String locator) {
		return By.xpath(locator);
	}

	public WebElement findElementByXpath(WebDriver driver, String locator) {
		return driver.findElement(byXpath(locator));
	}

	public List<WebElement> findElementsByXpath(WebDriver driver, String locator) {
		return driver.findElements(By.xpath(locator));
	}

	public String castToObject(String locator, String... values) {
		return String.format(locator, (Object[]) values);
	}

	public void clickToElement(WebDriver driver, String locator) {
		findElementByXpath(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... values) {
		findElementByXpath(driver, castToObject(locator, values)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		element = findElementByXpath(driver, locator);
		element.clear();
		element.sendKeys(value);
	}

	public void sendkeyToElement(WebDriver driver, String locator, String value, String... values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		element.clear();
		element.sendKeys(value);
	}

	public String getElementText(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).getText().trim();
	}
	
	public String getElementText(WebDriver driver, String locator, String ...values) {
		return findElementByXpath(driver, castToObject(locator, values)).getText().trim();
	}

	public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		return findElementByXpath(driver, locator).getAttribute(attributeName);
	}

	public void selectValueInDropDown(WebDriver driver, String locator, String value) {
		select = new Select(findElementByXpath(driver, locator));
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInDropDown(WebDriver driver, String locator) {
		select = new Select(findElementByXpath(driver, locator));
		return select.getFirstSelectedOption().getText();
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String allItemXpath,
			String expectedValueItem) {
		element = findElementByXpath(driver, parentXpath);

		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", element);
		sleepInSeconds(1);

		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byXpath(allItemXpath)));

		elements = findElementsByXpath(driver, allItemXpath);

		for (WebElement childElement : elements) {
			if (childElement.getText().equals(expectedValueItem)) {
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSeconds(1);
					jsExecutor.executeScript("arguments[0].click();", childElement);
				}
				sleepInSeconds(1);
				break;
			}
		}
	}

	public int countElementNumber(WebDriver driver, String locator) {
		elements = findElementsByXpath(driver, locator);
		return elements.size();
	}

	public int countElementNumber(WebDriver driver, String locator, String... values) {
		elements = findElementsByXpath(driver, castToObject(locator, values));
		return elements.size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (!element.isSelected()) {
			clickToElementByJS(driver, locator);
		}
	}
	
	public void checkToCheckbox(WebDriver driver, String locator, String...values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		if (!element.isSelected()) {
			clickToElementByJS(driver, castToObject(locator, values));
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		if (element.isSelected()) {
			clickToElementByJS(driver, locator);
		}
	}
	
	public void uncheckToCheckbox(WebDriver driver, String locator, String...values) {
		element = findElementByXpath(driver, castToObject(locator, values));
		if (element.isSelected()) {
			clickToElementByJS(driver, castToObject(locator, values));
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		try {
			return findElementByXpath(driver, locator).isDisplayed();
		} catch (NoSuchElementException noSuchException) {
			noSuchException.printStackTrace();
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		return findElementByXpath(driver, castToObject(locator, values)).isDisplayed();
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, locator);

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator, String... values) {
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		elements = findElementsByXpath(driver, castToObject(locator, values));

		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locator) {
		return findElementByXpath(driver, locator).isSelected();
	}

	public void switchToFrameOrIframe(WebDriver driver, String locator) {
		element = findElementByXpath(driver, locator);
		driver.switchTo().frame(element);
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.moveToElement(findElementByXpath(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.doubleClick(findElementByXpath(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		action = new Actions(driver);
		action.contextClick(findElementByXpath(driver, locator)).perform();
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, locator), key).perform();
	}
	
	public void clearTextonTextarea(WebDriver driver, String locator){
		element = findElementByXpath(driver, locator);
	    while(!element.getAttribute("value").equals("")){
	    	element.sendKeys(Keys.chord(Keys.COMMAND,"a"));
	    	element.sendKeys(Keys.BACK_SPACE);
	    }
	}
	
	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key, String... values) {
		action = new Actions(driver);
		action.sendKeys(findElementByXpath(driver, castToObject(locator, values)), key).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String expectedText) {
		jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + expectedText + "')[0]");
		return textActual.equals(expectedText);
	}

	public void scrollToBottomPage(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = findElementByXpath(driver, locator);

		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSeconds(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void clickToElementByJS(WebDriver driver, String locator, String...values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.xpath(castToObject(locator, values))));
	}
	
	public void scrollToElement(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, locator));
	}
	
	public void scrollToElement(WebDriver driver, String locator, String...values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", findElementByXpath(driver, castToObject(locator, values)));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",
				findElementByXpath(driver, locator));
	}
	
	public void sendkeyToElementByJS(WebDriver driver, String locator, String value, String...values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')",
				findElementByXpath(driver, castToObject(locator, values)));
	}

	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				findElementByXpath(driver, locator));
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove, String...values) {
		jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				findElementByXpath(driver, castToObject(locator, values)));
	}

	public String getElementValidationMessage(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				findElementByXpath(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor
				.executeScript(
						"return arguments[0].complete && typeof arguments[0]"
								+ ".naturalWidth != 'undefined' && arguments[0]." + "naturalWidth > 0",
						findElementByXpath(driver, locator));
		if (status) {
			return true;
		}
		return false;
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator, String...values) {
		jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor
				.executeScript(
						"return arguments[0].complete && typeof arguments[0]"
								+ ".naturalWidth != 'undefined' && arguments[0]." + "naturalWidth > 0",
						findElementByXpath(driver, castToObject(locator, values)));
		if (status) {
			return true;
		}
		return false;
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(locator)));
	}
	
	public void waitForElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.visibilityOfAllElements(elements));
	}

	public void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(byXpath(castToObject(locator, values))));
	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(byXpath(locator)));
	}

	public void waitForElementsInvisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		elements = findElementsByXpath(driver, locator);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}

	public void waitForElementClickable(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(locator)));
	}

	public void waitForElementClickable(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(byXpath(castToObject(locator, values))));
	}

	public void UploadMultipleFiles(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + GlobalConstants.UPLOAD_FOLDER + file + "\n";
		}
		fullFileName = fullFileName.trim();
		sendkeyToElement(driver, AbstractWordPressPageUI.UPLOAD_FILE_TYPE, fullFileName);
	}

	public boolean areFileUploadedDisplayed(WebDriver driver, String... fileNames) {
		boolean status = false;
		int number = fileNames.length;
		waitForElementsInvisible(driver, AbstractWordPressPageUI.MEDIA_LOADING_ICON);
		elements = findElementsByXpath(driver, AbstractWordPressPageUI.ALL_UPLOADED_IMAGE);

		List<String> imageValues = new ArrayList<String>();

		int i = 0;
		for (WebElement image : elements) {
			imageValues.add(image.getAttribute("src"));
			i++;
			if (i == number) {
				break;
			}
		}

		for (String fileName : fileNames) {
			String[] files = fileName.split("\\.");
			fileName = files[0].toLowerCase();

			status = imageValues.contains(fileName);

			for (i = 0; i < imageValues.size(); i++) {
				if (!imageValues.get(i).contains(fileName)) {
					status = false;
					if (i == imageValues.size() - 1) {
						return status;
					}
				} else {
					status = true;
					break;
				}
			}
		}
		return status;
	}

	public PostsPageObject clickToPostsMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractWordPressPageUI.POSTS_LINK);
		clickToElement(driver, AbstractWordPressPageUI.POSTS_LINK);
		return PageGeneratorManager.getPostsAdminPage(driver);
	}

	public PagesPageObject clickToPagesMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractWordPressPageUI.PAGES_LINK);
		clickToElement(driver, AbstractWordPressPageUI.PAGES_LINK);
		return PageGeneratorManager.getPagesAdminPage(driver);
	}

	public MediaPageObject clickToMediaMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractWordPressPageUI.MEDIA_LINK1);
		clickToElement(driver, AbstractWordPressPageUI.MEDIA_LINK1);
		return PageGeneratorManager.getMediaAdminPage(driver);
	}

	public PostsPageObject clickToPostMenu(WebDriver driver) {
		waitForElementClickable(driver, AbstractWordPressPageUI.POST_LINK);
		clickToElement(driver, AbstractWordPressPageUI.POST_LINK);
		return PageGeneratorManager.getPostsAdminPage(driver);
	}

	public AbstractPage openMenuPageByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractWordPressPageUI.DYNAMC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractWordPressPageUI.DYNAMC_PAGE_LINK, pageName);

		if (pageName.equals("Home")) {
			return PageGeneratorManager.getDashboardAdminPage(driver);
		} else if (pageName.equals("Posts")) {
			return PageGeneratorManager.getPostsAdminPage(driver);
		} else if (pageName.equals("Pages")) {
			return PageGeneratorManager.getPagesAdminPage(driver);
		} else if (pageName.equals("Media")) {
			return PageGeneratorManager.getMediaAdminPage(driver);
		} else {
			return PageGeneratorManager.getDashboardAdminPage(driver);
		}
	}

	public void openMenuPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, AbstractWordPressPageUI.DYNAMC_PAGE_LINK, pageName);
		clickToElement(driver, AbstractWordPressPageUI.DYNAMC_PAGE_LINK, pageName);
	}
	
	public void openWordPressMenuPageByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, NewOrEditPostPageUI.BUTTON_LINK, pageName);
		clickToElementByJS(driver, NewOrEditPostPageUI.BUTTON_LINK, pageName);
	}
	
	public void inputToDynamicTextbox(WebDriver driver, String nameAttributeValue, String inputValue) {
		waitForElementVisible(driver, AbstractBankPageUI.DYNAMIC_TEXTBOX, nameAttributeValue);
		if(nameAttributeValue.equals("dob")) {
			removeAttributeInDOM(driver, AbstractBankPageUI.DYNAMIC_TEXTBOX, "type", nameAttributeValue);
			sleepInSeconds(1);
		}
		sendkeyToElement(driver, AbstractBankPageUI.DYNAMIC_TEXTBOX, inputValue, nameAttributeValue);
	}
	
	public void inputToDynamicTextArea(WebDriver driver, String nameAttributeValue, String inputValue) {
		waitForElementVisible(driver, AbstractBankPageUI.DYNAMIC_TEXT_AREA, nameAttributeValue);
		sendkeyToElement(driver, AbstractBankPageUI.DYNAMIC_TEXT_AREA, inputValue, nameAttributeValue);
	}

	public void clickToDynamicButton(WebDriver driver, String buttonValue) {
		waitForElementClickable(driver, AbstractBankPageUI.DYNAMIC_BUTTON, buttonValue);
		clickToElement(driver, AbstractBankPageUI.DYNAMIC_BUTTON, buttonValue);
	}
	
	public void clickToDynamicRadioButton(WebDriver driver, String radioButtonValue) {
		waitForElementClickable(driver, AbstractBankPageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
		clickToElement(driver, AbstractBankPageUI.DYNAMIC_RADIO_BUTTON, radioButtonValue);
	}
	
	public void clickToDynamicLink(WebDriver driver, String linkPageName) {
		waitForElementClickable(driver, AbstractBankPageUI.DYNAMIC_LINK, linkPageName);
		clickToElement(driver, AbstractBankPageUI.DYNAMIC_LINK, linkPageName);
	}
	
	public boolean isDynamicMessageDisplayed(WebDriver driver, String messageText) {
		waitForElementVisible(driver, AbstractBankPageUI.DYNAMIC_MESSAGE, messageText);
		return isElementDisplayed(driver, AbstractBankPageUI.DYNAMIC_MESSAGE, messageText);
	}
	
	public String getDynamicValueByColumnName(WebDriver driver, String columnName) {
		waitForElementVisible(driver, AbstractBankPageUI.DYNAMIC_VALUE_BY_COLUMN_NAME, columnName);
		return getElementText(driver, AbstractBankPageUI.DYNAMIC_VALUE_BY_COLUMN_NAME, columnName);
	}
	
	public HomePageObject openHomeUserPage(WebDriver driver) {
		openUrl(driver, GlobalConstants.USER_WORDPRESS_URL);
		return PageGeneratorManager.getHomeUserPage(driver);
	}
	
	public SearchResultPageObject inputToSearchTextboxUserPage(WebDriver driver, String postTitle) {
		waitForElementVisible(driver, AbstractWordPressPageUI.SEARCH_ICON);
		clickToElement(driver, AbstractWordPressPageUI.SEARCH_ICON);
		sendkeyToElement(driver, AbstractWordPressPageUI.SEARCH_TEXTBOX, postTitle);
		clickToElement(driver, AbstractWordPressPageUI.SEARCH_BUTTON);
		return PageGeneratorManager.getSearchResultUserPage(driver);
	}
	
	public DashboardPageObject openAdminPage(WebDriver driver) {
		openUrl(driver, GlobalConstants.ADMIN_WORDPRESS_URL);
		return PageGeneratorManager.getDashboardAdminPage(driver);
	}
		
	public boolean isPostDisplayedOnSearchPost(WebDriver driver, String authorName, String postTitle) {
		waitForElementVisible(driver, AbstractWordPressPageUI.DYNAMIC_POST_WITH_AUTHOR_TITLE, authorName, postTitle);
		return isElementDisplayed(driver, AbstractWordPressPageUI.DYNAMIC_POST_WITH_AUTHOR_TITLE, authorName, postTitle);
	}
	
	public boolean isPostDisplayedOnLatestPost(WebDriver driver, String categoryName, String postTitle, String dateCreated) {
		waitForElementVisible(driver, AbstractWordPressPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, postTitle, dateCreated);
		return isElementDisplayed(driver, AbstractWordPressPageUI.DYNAMIC_POST_WITH_CATEGORY_TITLE_DATE, categoryName, postTitle, dateCreated);
	}	
	
	public boolean isPostImageDisplayedAtPostTitleName(WebDriver driver, String postTitle, String imageName) {
		String[] files = imageName.split("\\.");
		String fileName = files[0].toLowerCase();
		waitForElementVisible(driver, AbstractWordPressPageUI.DYNAMIC_POST_IMAGE_BY_TITLE, postTitle, fileName);
		return isElementDisplayed(driver, AbstractWordPressPageUI.DYNAMIC_POST_IMAGE_BY_TITLE, postTitle, fileName) &&
				isImageLoaded(driver, AbstractWordPressPageUI.DYNAMIC_POST_IMAGE_BY_TITLE, postTitle, fileName);
	}
	
	public PostDetailPageObject clickToPostDetailWithTitleName(WebDriver driver, String postTitle) {
		waitForElementVisible(driver, AbstractWordPressPageUI.DYNAMIC_POST_TITLE, postTitle);
		clickToElementByJS(driver, AbstractWordPressPageUI.DYNAMIC_POST_TITLE, postTitle);
		return PageGeneratorManager.getPostDetailUserPage(driver);	
	}
	
	public boolean isDataSortedAscending(WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<String> arrayList = new ArrayList<String>();

		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (String name : sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}
	
	public boolean isDataSortedDescending(WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<String> arrayList = new ArrayList<String>();

		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(arrayList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}
		// Reverse data to sort DESC 
		Collections.reverse(arrayList);
		
		System.out.println(" ------------ Dữ liệu đã SORT DES trong Code: ------------ ");
		for (String name : arrayList) {
			System.out.println(name);
		}
		
		
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}
	
	public boolean isPriceSortedAscending (WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<Float> arrayList = new ArrayList<Float>();

		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);

		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}
	
	public boolean isPriceSortedDescending(WebDriver driver, String locator) {
		// Khai báo 1Array List
		ArrayList<Float> arrayList = new ArrayList<Float>();
		// Tìm tất cả element matching VS điều kiện (Name/ Price/..)
		List<WebElement> elementList = findElementsByXpath(driver,locator);
		// Lấy text của từng element add vào Array List
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").trim()));
		}

		System.out.println(" ------------ Dữ liệu trên UI: ------------ ");
		for (Float name : arrayList) {
			System.out.println(name);
		}

		// Copy qua 1 array list mới để SORT trong Code
		ArrayList<Float> sortedList = new ArrayList<>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		// Thực hiện SORT ASC
		Collections.sort(sortedList);

		System.out.println(" ------------ Dữ liệu đã SORT ASC trong Code: ------------ ");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		// Reverse data to sort DESC 
		Collections.reverse(sortedList);
		
		System.out.println(" ------------ Dữ liệu đã SORT DES trong Code: ------------ ");
		for (Float name : sortedList) {
			System.out.println(name);
		}
		
		
		// Verify 2 array bằng nhau - nếu dữ liệu sort trên UI ko chính xác thì kết quả trả về sai
		return sortedList.equals(arrayList);
	}

	private Select select;
	private Actions action;
	private JavascriptExecutor jsExecutor;
	private WebDriverWait explicitWait;
	private WebElement element;
	private List<WebElement> elements;
	private long longTimeout = 30;
}
