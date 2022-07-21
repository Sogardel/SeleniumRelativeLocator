package utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	
	
	public static ThreadLocal<WebDriver> driver =  new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(String browser) {
		
		switch(browser.toLowerCase()) {
			
		case "chrome" :
		//	System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
			driver.set(new ChromeDriver());
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			long chromeId = Thread.currentThread().getId();
			System.out.println("Chrome ---> Thread id : " + chromeId);
			
		return driver.get();	
		
		case "firefox" :
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
			driver.set(new FirefoxDriver());
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			long firefoxId = Thread.currentThread().getId();
			System.out.println("Chrome ---> Thread id : " + firefoxId);
		return driver.get();	
		
		default:
			return driver.get();

		}
		
	}
	
	

}
