package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.GenericWebMethods;

public class Logout {
	WebDriver driver;
	GenericWebMethods genericWebMethods;
	
	By menuDropDown = By.xpath("//span[@class='k-icon k-menu-expand-arrow k-i-arrow-60-down ng-star-inserted']");
	By logoutButton = By.xpath("//*[text()='Log Out']");
	
	public Logout(WebDriver driver) {
		this.driver=driver;
		genericWebMethods =new GenericWebMethods(driver);
		System.out.println("logut");
		
	}
	
	public void clickLogout() throws InterruptedException {
		System.out.println("logout");
	//	LoginPage.browserSync();
		genericWebMethods.clickWebElement(menuDropDown);
		//driver.findElement(menuDropDown).click();
		Thread.sleep(5000);
		genericWebMethods.clickWebElement(logoutButton);
		//driver.findElement(logoutButton).click();
	}
	
	

}
