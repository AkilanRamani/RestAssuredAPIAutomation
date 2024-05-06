package APIAutomation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameDemo {

	
	
	@SuppressWarnings("deprecation")
	
	public static void main(String[] args) {
        
	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		String URL = "https://selenium08.blogspot.com/2019/11/selenium-iframe.html";

		driver.get(URL);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		int iFrameSize = driver.findElements(By.tagName("iframe")).size();
		System.out.println("Toal no of iframes: " + iFrameSize);

		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//input[@itemprop = 'query-input']")).sendKeys("Selenium Jobs");
		driver.findElement(By.xpath("//button[@class = 'wpb_button wpb_btn-inverse btn']")).click();

		driver.switchTo().defaultContent();

		String getTitleOfMain = driver.getTitle();
		System.out.println("Title of main web page: " + getTitleOfMain);

		String mainPageURL = driver.getCurrentUrl();
		System.out.println("URL of main web page: " + mainPageURL);

		driver.switchTo().frame(1);
		driver.findElement(By.xpath("//input[@value = 'Register with us']")).click();

		driver.quit();
	}
}
