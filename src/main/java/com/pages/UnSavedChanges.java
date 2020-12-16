package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.GenericWebMethods;

public class UnSavedChanges {

	WebDriver driver;
	GenericWebMethods genericWebMethods;
	
	By yesButton =By.xpath("//button[text()=' Yes ']");
	By noButton =By.xpath("//button[text()=' No ']");
	By cancelButton =By.xpath("//*[text()=' Unsaved Changes ']//..//..//..//button[text()=' Cancel ']");
	
	//button[text()=' Yes ']

	public UnSavedChanges(WebDriver driver) {
		this.driver = driver;
		genericWebMethods =new GenericWebMethods(driver);
	}
	
	public void clickYes() {
		genericWebMethods.clickWebElement(yesButton);
		//driver.findElement(yesButton).click();
	}
	
	public void clickNo() {
		genericWebMethods.clickWebElement(noButton);
		//driver.findElement(noButton).click();
	}
	
	
	public void clickCancel() {
		genericWebMethods.clickWebElement(cancelButton);
		//driver.findElement(cancelButton).click();
	}
	
	
}
