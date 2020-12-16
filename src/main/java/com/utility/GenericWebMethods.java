package com.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


/**
 * @author vamsi
 *
 */
public class GenericWebMethods {

	protected WebDriver webdriver;
	//private EnvironmentCaller ec = new EnvironmentCaller();
	private static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public GenericWebMethods(WebDriver webdriver) {
		this.webdriver = webdriver;
	}

	/**
	 * Function will generate By object and return it E.g., By.id("id");
	 * 
	 * @author vamsi
	 * @param strLocator
	 *            Pass "id" for By.id, "xpath" for By.xpath, "name" for By.name,
	 *            "tagName" for By.tagName, "className" for By.className,
	 *            "cssSelector" for By.cssSelector
	 * @param strIdentifier
	 *            Value for the locator E.g., if "id" is passed for strLocator then
	 *            set this parameter to id of the element
	 * @return Return By selenium object
	 */
	public By getBy(String strLocator, String strIdentifier) {

		By locator = null;
		switch (strLocator) {
		case "id":
			// by Id
			locator = By.id(strIdentifier);
			break;
		case "xpath":
			// by xPath
			locator = By.xpath(strIdentifier);
			break;
		case "name":
			// by name
			locator = By.name(strIdentifier);
			break;
		case "tagName":
			// by tag name
			locator = By.tagName(strIdentifier);
			break;
		case "className":
			// by class name
			locator = By.className(strIdentifier);
			break;
		case "cssSelector":
			// by cssSelector
			locator = By.cssSelector(strIdentifier);
			break;
		case "linkText":
			// by linkText
			locator = By.linkText(strIdentifier);
			break;
		case "partialLinkText":
			// by partialLinkText
			locator = By.partialLinkText(strIdentifier);
			break;
		default:
			Assert.fail("Invalid parameter passed to getBy function. Parameter: " + strLocator);

		}
		return locator;

	}

	/**
	 * Function to return requested web element
	 * 
	 * @author vamsi
	 * @param locator
	 *            by object of the type By
	 * @return Requested web element. If element is not found, it will return null
	 */
	public WebElement getWebElement(By locator) {

		LOGGER.info("getWebElement: " + locator);
		try {
			if (waitForElementToAppear(locator, (int) GenericVariable.intElementWaitTimeout)) {
				return webdriver.findElement(locator);
			} else {
				LOGGER.info("getWebElement - " + locator.toString() + ", " + "not visible");
				//Assert.assertTrue(false, "the element is not visible: clickWebElement - " + locator.toString());
				return null;
			}

		} catch (NoSuchElementException e) {
			LOGGER.info("No Such Element Exception: getWebElement - " + locator + " Details: " + e.getMessage());
			return null;
		} catch (Exception e) {
			LOGGER.info("Exception: getWebElement - " + locator + " Details: " + e.getMessage());
			return null;
		}

	}

	/**
	 * Function to return requested web element list
	 * 
	 * @author vamsi
	 * @param locator
	 *            object of the type By
	 * @return Requested web element list
	 */
	public List<WebElement> getWebElements(By locator) {

		LOGGER.info("getWebElementsList: " + locator.toString());
		List<WebElement> objElements = null;

		try {
			objElements = webdriver.findElements(locator);
		} catch (Exception e) {
			LOGGER.info("getWebElementsList: " + e.getMessage());
		}

		return objElements;
	}

	/**
	 * Function to enter text in text field web element
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @param strInputText
	 *            Text to be entered
	 * @param boolClear
	 *            Pass true if you want to clear existing element before entering
	 *            text, else pass false
	 */
	public void enterText(WebElement webelement, String strInputText, boolean boolClear) {

		LOGGER.info("enterText: " + webelement.toString() + ", " + strInputText);
		try {
			if (waitForElementToBeVisbile(webelement)) {
				if (boolClear) {
					webelement.clear();
				}
				webelement.sendKeys(strInputText);
			} else {
				LOGGER.info("enterText - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: enterText - " + webelement.toString());
			}

		} catch (Exception e) {
			LOGGER.info("enterText - " + webelement.toString() + ", Exception: " + e.getMessage());
			Assert.assertTrue(false, "Could not perform the operation: enterText - " + webelement.toString());
		}

	}
	
	public void enterText(By by, String strInputText, boolean boolClear) {
		WebElement webelement=webdriver.findElement(by);

		LOGGER.info("enterText: " + webelement.toString() + ", " + strInputText);
		try {
			if (waitForElementToBeVisbile(webdriver.findElement(by))) {
				if (boolClear) {
					webelement.clear();
				}
				webelement.sendKeys(strInputText);
			} else {
				LOGGER.info("enterText - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: enterText - " + webelement.toString());
			}

		} catch (Exception e) {
			LOGGER.info("enterText - " + webelement.toString() + ", Exception: " + e.getMessage());
			Assert.assertTrue(false, "Could not perform the operation: enterText - " + webelement.toString());
		}

	}

	public void enterText(By by, Keys key, boolean boolClear) {
		WebElement webelement=webdriver.findElement(by);

		//LOGGER.info("enterText: " + webelement.toString() + ", " + strInputText);
		try {
			if (waitForElementToBeVisbile(webdriver.findElement(by))) {
				if (boolClear) {
					webelement.clear();
				}
				webelement.sendKeys(key);
			} else {
				LOGGER.info("enterText - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: enterText - " + webelement.toString());
			}

		} catch (Exception e) {
			LOGGER.info("enterText - " + webelement.toString() + ", Exception: " + e.getMessage());
			Assert.assertTrue(false, "Could not perform the operation: enterText - " + webelement.toString());
		}

	}
	
	/**
	 * Function to perform click operation on the web element
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 */
	public void clickWebElement(WebElement webelement) {

		LOGGER.info("clickWebElement: " + webelement.toString());
		try {
			if (waitForElementToBeVisbile(webelement)) {
				webelement.click();
			} else {
				LOGGER.info("clickWebElement - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: clickWebElement - " + webelement.toString());
			}
		} catch (Exception e) {
			LOGGER.info("clickWebElement - " + webelement.toString() + ", " + e.getMessage());
			Assert.assertTrue(false, "Could not perform the operation: clickWebElement - " + webelement.toString());
		}

	}
	
	public void clickWebElement(By by) {
		WebElement webelement= webdriver.findElement(by);

		LOGGER.info("clickWebElement: " + webelement.toString());
		try {
			if (waitForElementToBeVisbile(webelement)) {
				webelement.click();
			} else {
				LOGGER.info("clickWebElement - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: clickWebElement - " + webelement.toString());
			}
		} catch (Exception e) {
			LOGGER.info("clickWebElement - " + webelement.toString() + ", " + e.getMessage());
			Assert.assertTrue(false, "Could not perform the operation: clickWebElement - " + webelement.toString());
		}

	}

	/**
	 * Function to read the text value of the web element
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @return Returns text value of the web element. Returns null in case of any
	 *         error while reading the value
	 */
	public String readWebElementText(WebElement webelement) {

		LOGGER.info("readWebElementText: " + webelement.toString());
		try {
			if (waitForElementToBeVisbile(webelement)) {
				return webelement.getText();
			} else {
				LOGGER.info("readWebElementText - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: readWebElementText - " + webelement.toString());
			}

		} catch (Exception e) {
			LOGGER.info("readWebElementText - " + webelement.toString() + ", " + e.getMessage());
			Assert.assertTrue(false, "Could not perform the operation: readWebElementText - " + webelement.toString());
		}
		return null;

	}

	/**
	 * Function to return the attribute value of web element
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @param strAttributeName
	 *            = the value that needs to be returned. If we want the value of
	 *            attribute class then pass the string"class"
	 * 
	 * @return Function will return the value of the specified tag attribute
	 */
	public String getTagAttribute(WebElement webelement, String strAttributeName) {

		LOGGER.info("getTagAttribute: " + webelement.toString() + ", " + strAttributeName);
		try {
			if (waitForElementToBeVisbile(webelement)) {
				return webelement.getAttribute(strAttributeName);
			} else {
				LOGGER.info("getTagAttribute - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: getTagAttribute - " + webelement.toString());
			}

		} catch (Exception e) {
			LOGGER.info("getTagAttribute - " + webelement.toString() + ", " + strAttributeName + ", " + e.getMessage());
		}
		return null;

	}

	/**
	 * Function: getXYCoordinates
	 * 
	 * @author vamsi
	 * @param intXY
	 *            Pass int value 1 for getting X co-ordinates & 2 for getting Y
	 *            co-ordinates
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @return Function will return X or Y co-ordinates based on the the parameters
	 *         sent
	 */
	public int getXYCoordinates(int intXY, WebElement webelement) {

		LOGGER.info("getXYCoordinates: " + intXY + ", " + webelement.toString());
		int intCoordinate;
		Point pointXY = null;
		try {
			if (waitForElementToBeVisbile(webelement)) {
				pointXY = webelement.getLocation();
			} else {
				LOGGER.info("getXYCoordinates - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: getXYCoordinates - " + webelement.toString());
			}
		} catch (Exception e) {
			LOGGER.info("getTagAttributeByIdentifier - " + webelement.toString() + ", " + e.getMessage());
		}

		if (intXY == 1) {
			// return x coordinate
			intCoordinate = pointXY.getX();
		} else {
			// return Y coordinate
			intCoordinate = pointXY.getY();
		}

		LOGGER.info("getXYCoordinates: value " + intCoordinate);
		return intCoordinate;

	}

	/**
	 * Function to check if element is visible on the screen
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @return Returns true if element is visible, else returns false
	 */
	public boolean isElementVisible(WebElement webelement) {

		LOGGER.info("isElementVisible: " + webelement.toString());
		try {
			if (waitForElementToBeVisbile(webelement)) {
				return webelement.isDisplayed();
			} else {
				LOGGER.info("isElementVisible - " + webelement.toString() + ", " + "not visible");
				//Assert.assertTrue(false, "the element is not visible: isElementVisible - " + webelement.toString());
				return false;
			}
		} catch (Exception e) {
			LOGGER.info("isElementVisible - " + webelement.toString() + ", " + e.getMessage());
			return false;
		}

	}

	/**
	 * Function to wait for the element to be visible on the screen. Function for
	 * checking that an element, known to be present on the DOM of a page, is
	 * visible. Visibility means that the element is not only displayed but also has
	 * a height and width that is greater than 0. The function will wait till the
	 * timeout specified in the parameter (in seconds)
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @param timeout
	 *            number of seconds to wait
	 * @return Returns true if the element becomes visible, else returns false
	 */
	public boolean waitForElementToBeVisbile(WebElement webelement) {

		LOGGER.info("waitForElementToVisbile: " + webelement.toString());
		WebDriverWait wait = new WebDriverWait(webdriver, GenericVariable.intElementWaitTimeout);

		try {
			wait.until(ExpectedConditions.visibilityOf(webelement));
			return true;
		} catch (Exception e) {
			LOGGER.info("waitForElementToVisbile - " + webelement.toString() + ", " + e.getMessage());
			return false;
		}

	}

	/**
	 * Function to wait for specific text to be present in an element. The function
	 * will wait till the timeout specified in the parameter (in seconds)
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @param strText
	 *            Text for which we need to wait.  Should be as it appears in the
	 *            application without CSS formating for Edge Compatibility
	 * @param timeout
	 *            number of seconds to wait
	 * @param browserSpecific
	 *            boolean to indicate if we should convert to uppercase           
	 * @return Returns true if text appears after waiting for specified seconds,
	 *         else returns false
	 */
	public boolean waitForTextToBePresentInElement(WebElement webelement, String strText, int timeout, boolean browserSpecific) {

		LOGGER.info("waitForTextToBePresentInElement: " + webelement.toString() + ", " + strText);
		WebDriverWait wait = new WebDriverWait(webdriver, timeout);
		if ((browserSpecific) && !(this.getBrowserType().equals(GenericVariable.EDGE))) {
			strText = strText.trim().toUpperCase();
		}
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(webelement, strText));
			return true;
		} catch (Exception e) {
			LOGGER.info("waitForTextToBePresentInElement - " + webelement.toString() + ", " + strText + ", "
					+ e.getMessage());

		}
		return false;

	}

	/**
	 * waitForElementToAppear - Function will wait till web element appears on the
	 * screen. This does not necessarily mean that the element is visible. Maximum
	 * it will wait till the time (seconds) specified in timeout variable.
	 * 
	 * @author vamsi
	 * @param locator
	 *            by object of the type By
	 * @return true, if element appeared, else false
	 */
	public boolean waitForElementToAppear(By locator, int timeout) {

		LOGGER.info("waitForElementToAppear: " + locator.toString() + ", timeout - " + timeout + "seconds");
		WebDriverWait wait = new WebDriverWait(webdriver, timeout);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return isElementPresent(locator);
		} catch (Exception e) {
			LOGGER.info("waitForElementToAppear - " + locator.toString() + ", " + e.getMessage());
			return false;
		}

	}

	/**
	 * isElementPresent - Function to check if the element is present or not
	 * 
	 * @author vamsi
	 * @param locator
	 *            by object of the type By
	 * @param exit
	 *            True if we want the script execution to stop in case element is
	 *            not present. Pass false if we want to continue with the execution
	 *            irrespective of the element status
	 * @return Returns true if the element is present, else returns false
	 */
	public boolean isElementPresent(By locator) {

		LOGGER.info("isElementPresent: " + locator.toString());
		try {
			webdriver.findElement(locator);
		} catch (NoSuchElementException e) {

			LOGGER.info(
					"No Such Element Exception - isElementPresent: - " + locator.toString() + ", " + e.getMessage());
			return false;

		} catch (Exception e) {

			LOGGER.info("isElementPresent: Exception - " + locator.toString() + ", " + e.getMessage());
			return false;
		}

		return true;

	}

	/**
	 * waitForElementToDisappear - Function will wait till web element disappears
	 * from the screen. Function for checking that an element is either invisible or
	 * not present on the DOM. The function will wait till the timeout specified in
	 * the parameter (in seconds)
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @param timeout
	 *            number of seconds to wait
	 * @return true, if element disappeared, else false
	 */
	public boolean waitForElementToDisappear(WebElement webelement, int timeout) {

		LOGGER.info("waitForElementToDisappear: " + webelement.toString());
		WebDriverWait wait = new WebDriverWait(webdriver, timeout);

		try {
			wait.until(ExpectedConditions.invisibilityOf(webelement));
			return true;
		} catch (Exception e) {
			LOGGER.info("waitForElementToDisappear - " + webelement.toString() + ", " + e.getMessage());
			return false;
		}

	}

	/**
	 * To get list of values from dropdown
	 * 
	 * @author vamsi
	 * 
	 * @param dropDown
	 *            web element dropdown
	 * 
	 * @param list
	 *            list of web elements displayed after clicking dropdown
	 * 
	 * @return returns the list of values from dropdown
	 */
	public List<String> getAllValuesFromDropdown(WebElement dropDown, List<WebElement> list) {

		LOGGER.info("getAllValuesFromDropdown: " + dropDown.toString() + ", " + list);
		dropDown.click();
		waitForElementToBeVisbile(list.get(0));
		List<WebElement> elList = list;
		List<String> valueList = new ArrayList<String>();

		for (WebElement e : elList) {
			// Won't read blank values
			if (!((e.getText().trim() == "") || e.getText().isEmpty())) {
				valueList.add(e.getText());
			}
		}
		dropDown.click();
		return valueList;

	}

	/**
	 * To select a value from dropdown by text
	 * 
	 * @author vamsi
	 * 
	 * @param dropDown
	 *            Web element dropdown
	 * @param list
	 *            web element list displayed after clicking dropdown
	 * @param text
	 *            Text to be selected from dropdown
	 * @return returns false if the element not found or matching text not found in
	 *         dropdown
	 */
	public boolean selectValueFromDropdown(WebElement dropDown, WebElement list, String text) {

		LOGGER.info("selectValueFromDropdown: " + dropDown.toString() + ", " + list + ", " + text);
		try {
			if (waitForElementToBeVisbile(dropDown)) {
				dropDown.click();
				list.findElement(By.xpath("[text()='" + text + "']")).click();
				LOGGER.info("selectValueFromDropdown value selected: " + text);
				return true;
			} else {
				LOGGER.info("selectValueFromDropdown - " + dropDown.toString() + ", " + "not visible");
				Assert.assertTrue(false,
						"the element is not visible: selectValueFromDropdown - " + dropDown.toString());
				return false;
			}

		} catch (NoSuchElementException ex) {
			LOGGER.warning("selectValueFromDropdown value not found: " + text);
			dropDown.click();
			return false;
		}

	}

	/**
	 * To get the header of dropdown
	 * 
	 * @author vamsi
	 * 
	 * @param dropDown
	 *            dropdown webelement
	 * @param header
	 *            header element of the dropdown
	 * @return returns the header text of dropdown
	 */
	public String getDropdownHeader(WebElement dropDown, WebElement header) {

		LOGGER.info("getDropdownHeader: " + dropDown.toString() + ", " + header.toString());
		if (waitForElementToBeVisbile(dropDown)) {
			dropDown.click();
			String text = header.getText();
			LOGGER.info("getDropdownHeader: " + text);
			dropDown.click();
			return text;
		} else {
			LOGGER.info("getDropdownHeader - " + dropDown.toString() + ", " + "not visible");
			Assert.assertTrue(false, "the element is not visible: getDropdownHeader - " + dropDown.toString());
			return null;
		}

	}

	/**
	 * Function to drag and drop one element onto another element
	 * 
	 * @param sourceWebelement
	 *            Web element to be dropped. Get the web element using the function
	 *            getWebElement
	 * @param targetWebelement
	 *            Web element where we need to drop the source element
	 */
	public void dragAndDrop(WebElement sourceWebelement, WebElement targetWebelement) {

		LOGGER.info("dragAndDrop: " + sourceWebelement.toString() + ", " + targetWebelement);
		Actions actions = new Actions(webdriver);
		try {
			if (waitForElementToBeVisbile(sourceWebelement)) {
				actions.dragAndDrop(sourceWebelement, targetWebelement).build().perform();
			} else {
				LOGGER.info("dragAndDrop - " + sourceWebelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: dragAndDrop - " + sourceWebelement.toString());
			}

		} catch (Exception e) {
			LOGGER.info("dragAndDrop: " + sourceWebelement.toString() + ", " + targetWebelement.toString() + ", "
					+ e.getMessage());
		}

	}

	/**
	 * Function to right click on a web element
	 * 
	 * @param webelement
	 *            Web element on which we need to operate. Get the web element using
	 *            the function getWebElement
	 */
	public void rightClick(WebElement webelement) {

		LOGGER.info("dragAndDrop: " + webelement.toString());
		Actions actions = new Actions(webdriver);
		try {
			if (waitForElementToBeVisbile(webelement)) {
				actions.contextClick(webelement).perform();
			} else {
				LOGGER.info("rightClick - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: rightClick - " + webelement.toString());
			}

		} catch (Exception e) {
			LOGGER.info("rightClick - " + webelement.toString() + ", " + e.getMessage());
		}

	}

	/**
	 * Waits for a StaleElementReferenceException or NoSuchElementException so that
	 * selenium will know when angular has refreshed the data as a result of a user
	 * action.
	 * 
	 * @param elementToEval
	 *            WebElement we expect to be stale
	 */
	public boolean isElementStale(WebElement aUiElement, By by) {
		boolean stale = false;
		while (!stale) {
			try {
				WebElement staleElement = aUiElement.findElement(by);
				stale = false;
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				stale = true;
			}
		}
		return stale;
	}

	public void refresh() {
		webdriver.navigate().refresh();
	}

	/**
	 * Bring the element visible
	 * 
	 * 
	 * @param WebElement
	 *            Scroll to Element to make it visible
	 * 
	 */
	public void scroll_TO_Element_To_Be_Visible(WebElement element) {
		Actions actions = new Actions(webdriver);
		actions.moveToElement(element);
		actions.perform();
	}

	public void click_Element(WebElement webelement) {
		LOGGER.info("start finding xpathValue: " + webelement);
		webelement.sendKeys(Keys.ENTER);

	}

	public String get_Page_Title() {

		return webdriver.getTitle();

	}

	public boolean waitForPageLoad() {
		LOGGER.setLevel(Level.INFO);
		return "complete".equals(((JavascriptExecutor) webdriver).executeScript("return document.readyState"));
	}
	
	/**
	 * scroll to the bottom of the page for TOS modal window| Auth: Ram T
	 */

	public void scroll_To_Bottom_Of_Modal_Window() {
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		js.executeScript("document.getElementsByClassName('tos-container')[0].scrollTop = document.getElementsByClassName('tos-container')[0].scrollHeight");
		((JavascriptExecutor) webdriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * A. if u want to scroll to the bottom of the page | Auth: Grace T
	 */

	public void scrool_To_Bottom_Of_Page() {
		((JavascriptExecutor) webdriver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * B. if u want to scroll to a specific element then try this | Auth: Grace T
	 * make sure you keep the argument as 0
	 */
	public void scrool_To_Element_Js(WebElement element) {
		// WebElement element = webdriver.findElement(By.xpath(element));
		((JavascriptExecutor) webdriver).executeScript("arguments[0].scrollIntoView();", element);
	}

	/**
	 * C. if u wan to scroll on the basis of coordinates then try this | Auth: Grace
	 * T make sure you copy this code and do not use the bellow as the javascript
	 * couldn't be param for now. Or please params this method and notify the team
	 */
	public void scrool_To_Element_Using_Cordinate_Xpath() {
		((JavascriptExecutor) webdriver).executeScript("window.scrollBy(0,500)");
	}

	/**
	 * Move mouse cursor to particular element , so that hover text is seen
	 * 
	 * @author vamsi
	 * @param by
	 *            Reference of the element to be hover
	 */
	public void mouse_Hover(By by) {
		WebElement element = webdriver.findElement(by);
		Actions action = new Actions(webdriver);
		action.moveToElement(element).build().perform();
	}

	/**
	 * Opens a new url on different tab and switches to it
	 * 
	 * @author vamsi
	 * 
	 * @param url
	 *            New url to be opened
	 */
	public void openUrlOnAnotherTab(String url) {
		// Opens a new tab and switch to it
		((JavascriptExecutor) webdriver).executeScript("window.open('about:blank', '-blank')");
		ArrayList<String> tabs = new ArrayList<String>(webdriver.getWindowHandles());
		navigateToTab(tabs.size() - 1);

		// Opens the url
		webdriver.get(url);
	}

	/**
	 * Navigates to the given tab of the browser
	 * 
	 * @author vamsi
	 */
	public void navigateToTab(int no) {
		ArrayList<String> tabs = new ArrayList<String>(webdriver.getWindowHandles());
		webdriver.switchTo().window(tabs.get(no));
	}

	/**
	 * The function will return the total count of child elements present in the
	 * parent element.
	 * 
	 * @param value
	 *            = the value to be looked by the wait method to determine to
	 *            perform the click operation.
	 * @param type
	 *            = Type specifies the value is associated with the locator.
	 * @return void
	 */
	public void pageWait(String value, String type) {
		WebDriverWait wait = new WebDriverWait(webdriver, 30, 500);
		if (type.equals("id")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.id(value)));
		} else if (type.equals("css")) {
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(value)));
		} else {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
		}
	}

	/**
	 * The function will return the inner width and inner height of the window which can
	 * be interpreted as the viewport size of the application
	 * 
	 * @return int[width, height]
	 */
	public Dimension getViewportDims() {
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		Dimension viewPort = new Dimension(
				((Number) js.executeScript("return window.innerWidth")).intValue(),
				((Number) js.executeScript("return window.innerHeight")).intValue());
		return viewPort;
	}
	
	/**
	 * Set the size of the browser window so that small, medium and large browser specifications
	 * an be measured
	 * 
	 * @author vamsi
	 * 
	 * @param width and height
	 * 
	 */
	public void setBrowserSize(int setWidth,int setHeight) {
		JavascriptExecutor js = (JavascriptExecutor) webdriver;
		
		Dimension window_size=new Dimension(
				((Number) js.executeScript("return window.outerWidth - window.innerWidth +"+setWidth)).intValue(),
				((Number) js.executeScript("return window.outerWidth - window.innerWidth +"+setHeight)).intValue());
		webdriver.manage().window().setSize(window_size);
	}
	
	/**
	 * Function to return the CSS style of web element
	 * 
	 * @author vamsi
	 * @param webelement
	 *            WebElement object - Web element on which we need to operate. Get
	 *            the web element using the function getWebElement
	 * @param strAttributeName
	 *            = the value that needs to be returned. If we want the value of
	 *            attribute class then pass the string"class"
	 * 
	 * @return Function will return the value of the specified tag attribute
	 */
	public String getCssValue(WebElement webelement, String strAttributeName) {

		LOGGER.info("getCssValue: " + webelement.toString() + ", " + strAttributeName);
		try {
			if (waitForElementToBeVisbile(webelement)) {
				return webelement.getCssValue(strAttributeName);
			} else {
				LOGGER.info("getTagAttribute - " + webelement.toString() + ", " + "not visible");
				Assert.assertTrue(false, "the element is not visible: getTagAttribute - " + webelement.toString());
			}

		} catch (Exception e) {
			LOGGER.info("getTagAttribute - " + webelement.toString() + ", " + strAttributeName + ", " + e.getMessage());
		}
		return null;

	}	
	/**
	 * Function to return the browser coupled to the webdriver
	 * 
	 * @author vamsi
	 * 
	 * @return Function will return the value of the specified tag attribute
	 */
	public String getBrowserType() {

		LOGGER.info("getBrowserType");
		Capabilities cap = ((RemoteWebDriver) webdriver).getCapabilities();
		return cap.getBrowserName();

	}	
}
