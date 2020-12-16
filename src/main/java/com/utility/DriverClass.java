package com.utility;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverClass {
	static WebDriver driver;

	public static WebDriver getDriver(String browser) {
	
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\GeneralPreetam\\chromedriver_win32\\chromedriver.exe");
				 driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				return driver;
				
			} else if (browser.equalsIgnoreCase("IE")) {
				System.setProperty("webdriver.ie.driver", "C:\\GeneralPreetam\\chromedriver_win32\\ie.exe");
				 driver = new InternetExplorerDriver();
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				driver.manage().window().maximize();
				return driver;
			}
		return driver;
	}

}
