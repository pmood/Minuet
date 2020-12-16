package automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FirstSelenium {
	public static void main(String[] args) {
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\GeneralPreetam\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://localhost/InteumWeb/Minuet/");
		
		WebDriverWait wait =new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
		driver.findElement(By.id("username")).sendKeys("preetadmin");
		driver.findElement(By.id("password")).sendKeys("p");
		driver.findElement(By.xpath("//button[@class = 'btn btn-primary']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Global Search']")));
		System.out.println(driver.findElement(By.xpath("//input[@placeholder='Global Search']")).isDisplayed());
		driver.findElement(By.xpath("//button[@class = 'k-button k-group-start ng-star-inserted k-button-icontext']")).click();
		//driver.close();
		
	}

}
class A{
	
}