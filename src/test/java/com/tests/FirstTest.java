package com.tests;

import org.openqa.selenium.WebDriver;

import com.pages.GrantsAwards;
import com.pages.Header;
import com.pages.LoginPage;
import com.utility.DriverClass;
import com.utility.RandomNumberCustom;

public class FirstTest {
	static WebDriver driver;
	//
	public static void main(String[] args) throws InterruptedException {
		driver=DriverClass.getDriver("chrome");
		driver.get("https://localhost/InteumWeb/Minuet/");
		LoginPage loginPage = new LoginPage(driver);
		/*
		loginPage.enteruserName("preetadmin");
		loginPage.enterPassword("p");
		loginPage.clickLogin();
		*/
		
		loginPage.enterLoginDetails("preetadmin", "p");
		Header header = new Header(driver);
		header.clickAddGrantsOrAwards();
		
		GrantsAwards grantsAwards = new GrantsAwards(driver);
		int num=RandomNumberCustom.getRandomNumber();
		String grantNo="abc"+num;
		grantsAwards.newForm("ade", "Title", "spo",grantNo );
		grantsAwards.clickGrantsAwards();
		grantsAwards.search(grantNo);
		grantsAwards.getCountofSearchResuts("1");
		grantsAwards.search("abc");
		grantsAwards.getCountofSearchResuts("1");
		grantsAwards.selectSearchResult();
		grantsAwards.setTitle("ABCDE");
		grantsAwards.checkElementChanged();
		//grantsAwards.clickCancel();
		grantsAwards.verifyChangesDiscarded();
		
		grantsAwards.clickCloseGrantAwards();
		grantsAwards.search("abc");
		grantsAwards.selectSearchResult();
		grantsAwards.setSecondTimeTitle("ABCDE");
		grantsAwards.verifyChangesAcceptSaved();
		
		
		
		
		//Logout logout = new Logout(driver);
		//logout.clickLogout();
		
		//driver.quit();
	}

}
