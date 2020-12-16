package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.GenericWebMethods;

public class LoginPage {

	 WebDriver driver;
	WebDriverWait wait;
	GenericWebMethods genericWebMethods;

	By userNameTextbox = By.id("username");
	By passWordTextbox = By.id("password");

	By loginButton = By.xpath("//button[@class = 'btn btn-primary']");
	By menuDropDown = By.xpath("//span[@class='k-icon k-menu-expand-arrow k-i-arrow-60-down ng-star-inserted']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 30);
		 genericWebMethods =new GenericWebMethods(driver);

	}

	public void enteruserName(String usvalue) {
		genericWebMethods.enterText(userNameTextbox, usvalue, true);
		//driver.findElement(userNameTextbox).sendKeys(usvalue);
	}

	public void enterPassword(String psvalue) {
		genericWebMethods.enterText(passWordTextbox, psvalue, true);
		//driver.findElement(passWordTextbox).sendKeys(psvalue);
	}

	public void clickLogin() {
		genericWebMethods.clickWebElement(loginButton);
		//driver.findElement(loginButton).click();
		//click_Xpath("//span[@class='k-icon k-menu-expand-arrow k-i-arrow-60-down ng-star-inserted']");
	}
	

	public void enterLoginDetails(String usname, String uspass) {
		enteruserName(usname);
		enterPassword(uspass);
		clickLogin();
		waitForHomePage();

	}

	public void waitForHomePage() {
		wait.until(ExpectedConditions.presenceOfElementLocated(menuDropDown));

	}
	public boolean browserSync() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			return wait.until(driver -> String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
							.equalsIgnoreCase("complete"));
		} catch (Exception e) {
			return browserSync();
		}
	}

}
