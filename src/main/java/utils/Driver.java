package utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Driver {
	
	
	public static ThreadLocal<WebDriver> driver =  new ThreadLocal<WebDriver>();
	
	public WebDriver initDriver(String browser) throws MalformedURLException {
		
		switch(browser.toLowerCase()) {
			
		case "chrome" :
		//	System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		/*	driver.set(new ChromeDriver());
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			long chromeId = Thread.currentThread().getId();
			System.out.println("Chrome ---> Thread id : " + chromeId);*/
			
			
			ChromeOptions options =  new ChromeOptions();
			options.addArguments("start-maximized");
			options.addArguments("--headless");
			driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), options));
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			long chromeId = Thread.currentThread().getId();
			System.out.println("Chrome ---> Thread id : " + chromeId);
			
		return driver.get();	
		
		case "firefox" :
			System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
		/*	driver.set(new FirefoxDriver());
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			long firefoxId = Thread.currentThread().getId();
			System.out.println("Chrome ---> Thread id : " + firefoxId); */
			
			FirefoxBinary firefoxBinary =  new FirefoxBinary();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);
			driver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), firefoxOptions));
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
