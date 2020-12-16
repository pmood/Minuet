package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.GenericWebMethods;

public class Header {

	WebDriver driver;
	GenericWebMethods genericWebMethods;
	By addGrantsOrAwards = By.xpath("//button[@title='Find Grants/Awards']//following-sibling::button");

	public Header(WebDriver driver) {
		this.driver = driver;
		 genericWebMethods =new GenericWebMethods(driver);

	}

	public void clickAddGrantsOrAwards() {
		genericWebMethods.clickWebElement(addGrantsOrAwards);
		//driver.findElement(addGrantsOrAwards).click();
	}
	

	// button[@data-title='Find Grants/Awards']//following-sibling::button

}
