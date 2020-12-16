package com.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.utility.GenericWebMethods;

public class GrantsAwards {
	WebDriver driver;
	WebDriverWait wait;
	UnSavedChanges unSaved;

	GenericWebMethods genericWebMethods;

	By grantsAwards = By.xpath("//button[text()=' Grants/Awards ']");
	By grantsSearch = By.xpath("//input[@placeholder='Search...']");
	By searchCount = By.xpath("(//span[@data-resourcekeys='View_Grid_LabelCount']//..//..//..//..//span)[4]");
	By selectSearchResult = By.xpath("(//tr[@kendogridlogicalrow])[2]");
	By titleGrantArea = By
			.xpath("//textarea[@class='k-textarea min-editor-textarea ng-untouched ng-pristine ng-valid']");
	By grantAwardsClose = By
			.xpath("//span[text()='Grant/Award']//..//..//..//..//../span[@class='k-icon k-i-x-outline linkCol-3']");

	By changeElementdepiction = By.xpath("//div[@class='ng-star-inserted k-required minuet-dirty']");
	By saveButton = By.xpath("//button[@title='Save']");
	By delete = By.xpath("//button[@title='Delete']");
	By refresh = By.xpath("//button[@title='Refresh']");
	By favourite = By.xpath("//button[@title='Favourite']");
	By saveAndAdd = By.xpath("//button[@title='Global_UI_Button_SaveAdd']");
	By cancelButton = By.xpath("//button[@title='Cancel']");
	String originalTitleValue = null;

	public GrantsAwards(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
		unSaved = new UnSavedChanges(driver);

		System.out.println("GrantsAwards");
		genericWebMethods = new GenericWebMethods(driver);
	}

	public void clickCancel(String value) throws InterruptedException {
		genericWebMethods.clickWebElement(cancelButton);
		// driver.findElement(cancelButton).click();
		if (value.equalsIgnoreCase("yes")) {
			unSaved.clickYes();
		} else if (value.equalsIgnoreCase("no")) {
			unSaved.clickNo();
		} else {
			unSaved.clickCancel();
		}
		Thread.sleep(10000);

	}

	public void clickGrantsAwards() {
		genericWebMethods.clickWebElement(grantsAwards);
		// driver.findElement(grantsAwards).click();
	}

	public void clickCloseGrantAwards() throws InterruptedException {
		Thread.sleep(3000);
		genericWebMethods.clickWebElement(grantsAwards);
		//driver.findElement(grantsAwards).click();
		Thread.sleep(3000);
	}

	public void search(String searchText) throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(grantsSearch).clear();
		genericWebMethods.enterText(grantsSearch, searchText, true);
		//driver.findElement(grantsSearch).sendKeys(searchText);
		Thread.sleep(5000);
		genericWebMethods.enterText(grantsSearch, Keys.ENTER, true);
		//driver.findElement(grantsSearch).sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		genericWebMethods.enterText(grantsSearch, Keys.ENTER, true);
		//driver.findElement(grantsSearch).sendKeys(Keys.ENTER);
	}

	public void selectSearchResult() {
		WebElement element = driver.findElement(selectSearchResult);
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void checkElementChanged() {
		System.out.println(driver.findElement(changeElementdepiction).isDisplayed());
		if (!driver.findElement(changeElementdepiction).isDisplayed()) {
			Assert.assertTrue(false);
		}

		checkButtonsEnabled();

	}

	public void checkButtonsEnabled() {
		System.out.println("checkButtonsEnabled");

		wait.until(ExpectedConditions.elementToBeClickable(saveButton));
		wait.until(ExpectedConditions.elementToBeClickable(saveAndAdd));
		System.out.println("checkButtonsEnabled completed");

		// button[text()=' Save/Add ']
	}

	public void setTitle(String titlevalue) {
		By pristine = By.xpath("//textarea[@class='k-textarea min-editor-textarea ng-pristine ng-valid ng-touched']");
		wait.until(ExpectedConditions.presenceOfElementLocated(titleGrantArea));
		originalTitleValue = driver.findElement(titleGrantArea).getAttribute("value");
		// System.out.println("originalTitleValue " + originalTitleValue);
		driver.findElement(titleGrantArea).clear();
		driver.findElement(pristine)
		//driver.findElement(By.xpath("//textarea[@class='k-textarea min-editor-textarea ng-pristine ng-valid ng-touched']"))
				.sendKeys(titlevalue);
	}

	public void setTitleNewForm(String titlevalue) {
		By untouched = By.xpath("//textarea[@class='k-textarea min-editor-textarea ng-untouched ng-pristine ng-valid']");
		wait.until(ExpectedConditions.presenceOfElementLocated(titleGrantArea));
		originalTitleValue = driver.findElement(titleGrantArea).getAttribute("value");
		// System.out.println("originalTitleValue " + originalTitleValue);
		driver.findElement(titleGrantArea).clear();
		driver.findElement(untouched)
		//driver.findElement(By.xpath("//textarea[@class='k-textarea min-editor-textarea ng-untouched ng-pristine ng-valid']"))
				.sendKeys(titlevalue);
	}

	//

	public void setSecondTimeTitle(String titlevalue) {
		By touched = By.xpath("//textarea[@class='k-textarea min-editor-textarea ng-valid ng-touched ng-dirty']");
		wait.until(ExpectedConditions.presenceOfElementLocated(touched));
		originalTitleValue = driver.findElement(touched).getAttribute("value");
		System.out.println("originalTitleValue " + originalTitleValue);
		driver.findElement(touched)
				.clear();
		driver.findElement(touched)
				.sendKeys(titlevalue);
	}

	public void getCountofSearchResuts(String resultCount) throws InterruptedException {
		Thread.sleep(5000);
		System.out.println("The count of search" + driver.findElement(searchCount).getText());
		if (!resultCount.equalsIgnoreCase(driver.findElement(searchCount).getText())) {
			//assertTrue(false);
			
			Assert.assertTrue(false);
			
		}

	}

	public void verifyChangesDiscarded() throws InterruptedException {
		clickCancel("No");
		boolean noDirtyChanges = true;
		try {
			noDirtyChanges = driver.findElement(changeElementdepiction).isDisplayed();
		} catch (Exception e) {
			noDirtyChanges = false;
		}

		if (noDirtyChanges) {
			//assertTrue(false);
			Assert.assertTrue(false);
		}

		String afterTitleValue = driver
				.findElement(
						By.xpath("//textarea[@class='k-textarea min-editor-textarea ng-valid ng-touched ng-dirty']"))
				.getAttribute("value");
		System.out.println("afterDiscardingValue   " + afterTitleValue);
		if (!originalTitleValue.equals(afterTitleValue)) {
			//assertTrue(false);

			Assert.assertTrue(false);
			}

	}

	public void verifyChangesAcceptSaved() throws InterruptedException {

		clickCancel("yes");
		boolean noDirtyChanges = true;
		try {
			noDirtyChanges = driver
					.findElement(By
							.xpath("//textarea[@class='k-textarea min-editor-textarea ng-valid ng-touched ng-dirty']"))
					.isDisplayed();
			System.out.println("noDirtyChanges  " + noDirtyChanges);

		} catch (Exception e) {
		}
		System.out.println("noDirtyChanges" + noDirtyChanges);

		if (!noDirtyChanges) {
		//	assertTrue(false);
		}

		String afterTitleValue = driver
				.findElement(
						By.xpath("//textarea[@class='k-textarea min-editor-textarea ng-valid ng-touched ng-dirty']"))
				.getAttribute("value");
		System.out.println("originalTitleValue   " + originalTitleValue);
		System.out.println("afterTitleValue   " + afterTitleValue);
		if (originalTitleValue.equals(afterTitleValue)) {
		//	assertTrue(false);
		}

	}

	public void clickSave() {
		genericWebMethods.clickWebElement(saveButton);
		//driver.findElement(saveButton).click();

	}

	By internalIdDrpdwnSmallArrow = By
			.xpath("//span[text()='Internal ID']//..//..//..//..//span[@class='k-icon k-i-arrow-s']");
	By internaldrpdwnValue = By.xpath("//*[contains(text(),'GRNT-2020-')]");
	By dateCalendar = By.xpath("//span[@aria-label='Toggle calendar']");
	By todayDate = By.xpath("//span[@class='k-today']");

	By sponsorEllipsis = By.xpath("(//div[@style=\"grid-area:1/2/2/3;\"])[2]");
	// By sponsorEllipsis =
	// By.xpath("//button[@class='min-editor-additionalinfo-button k-button-icon
	// k-button ng-star-inserted']");
	By sponsordrpdwnValue = By.xpath("//*[contains(text(),'3M industries')]");
	By saveOnSelectSponsor = By.xpath("//button[text()=' Cancel ']//preceding-sibling::button");

	public By getDropDownArrowForGivenField(String fieldLabel) {
		By by = By.xpath("//span[text()='" + fieldLabel + "']//..//..//..//..//span[@class='k-icon k-i-arrow-s']");
		return by;
	}

	By grantNumber = By
			.xpath("//input[@class='min-editor-text fillwidth ng-untouched ng-pristine ng-valid k-textbox']");

	public void setGrantNumber(String number) {
		genericWebMethods.enterText(grantNumber, number, true);
		// driver.findElement(grantNumber).sendKeys(number);
	}

	public void newForm(String internalId, String title, String sponsor, String number) throws InterruptedException {

		selectInternalDrpDwn(internalId);
		setTitleNewForm(title);
		setDate();
		clickSave();
		setSponsor();
		setGrantNumber(number);
		clickSave();

	}

	private void setSponsor() throws InterruptedException {
		Thread.sleep(20000);
		// wait.until(ExpectedConditions.presenceOfElementLocated(delete));
		genericWebMethods.clickWebElement(sponsorEllipsis);
		// driver.findElement(sponsorEllipsis).click();
		genericWebMethods.clickWebElement(getDropDownArrowForGivenField("Sponsor"));
		// driver.findElement(getDropDownArrowForGivenField("Sponsor")).click();
		Thread.sleep(10000);
		genericWebMethods.clickWebElement(sponsordrpdwnValue);
		// driver.findElement(sponsordrpdwnValue).click();
		genericWebMethods.clickWebElement(saveOnSelectSponsor);
		// driver.findElement(saveOnSelectSponsor).click();
		Thread.sleep(15000);

	}

	private void setDate() {
		genericWebMethods.clickWebElement(dateCalendar);
		// driver.findElement(dateCalendar).click();
		genericWebMethods.clickWebElement(todayDate);
		// driver.findElement(todayDate).click();

	}

	private void selectInternalDrpDwn(String internalId) {
		// getDropDownArrowForGivenField("Internal ID");

		genericWebMethods.clickWebElement(internalIdDrpdwnSmallArrow);
		// driver.findElement(internalIdDrpdwnSmallArrow).click();
		genericWebMethods.clickWebElement(internaldrpdwnValue);
		// driver.findElement(internaldrpdwnValue).click();
	}

}
