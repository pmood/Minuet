package com.tests;

import org.testng.annotations.Test;

import com.pages.GrantsAwards;
import com.pages.Header;
import com.pages.LoginPage;
import com.utility.DriverClass;
import com.utility.RandomNumberCustom;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class FirstTestTest {
	static WebDriver driver;

	@Test
	@Parameters({"userName"})
	public void Test(String userName) throws InterruptedException {
		
		System.out.println(userName+ " userName");

		Header header = new Header(driver);
		header.clickAddGrantsOrAwards();

		GrantsAwards grantsAwards = new GrantsAwards(driver);
		int num = RandomNumberCustom.getRandomNumber();
		String grantNo = "abc" + num;
		grantsAwards.newForm("ade", "Title", "spo", grantNo);
		grantsAwards.clickGrantsAwards();
		grantsAwards.search(grantNo);
		grantsAwards.getCountofSearchResuts("1");
		grantsAwards.search("abc");
		grantsAwards.getCountofSearchResuts("1");
		grantsAwards.selectSearchResult();
		grantsAwards.setTitle("ABCDE");
		grantsAwards.checkElementChanged();
		// grantsAwards.clickCancel();
		grantsAwards.verifyChangesDiscarded();

		grantsAwards.clickCloseGrantAwards();
		grantsAwards.search("abc");
		grantsAwards.selectSearchResult();
		grantsAwards.setSecondTimeTitle("ABCDE");
		grantsAwards.verifyChangesAcceptSaved();
	}

	@Test
	public void Login() {
		LoginPage loginPage = new LoginPage(driver);

		loginPage.enterLoginDetails("preetadmin", "p");
	}

	@BeforeSuite
	public void beforeSuite() {
		driver = DriverClass.getDriver("chrome");
		driver.get("https://localhost/InteumWeb/Minuet/");
	}

	@AfterSuite
	public void afterSuite() {

		driver.quit();
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("beforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("afterTest");
	}

}
