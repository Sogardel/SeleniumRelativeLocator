package exampleWindoHandle;

import java.awt.Desktop.Action;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.Driver;


public class ExampleWIndowHandle extends Driver {

	public WebDriver driver;
	public JavascriptExecutor jseExecutor; 
	String browser = System.getProperty("browser");

	//@Parameters({"browser"})
	@BeforeClass
	public void setUp() throws MalformedURLException {
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//Selenium 3 implicit wait	
		//driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		//Selenium 4 implict wait
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver = initDriver(browser);
		
		driver.get("https://keybooks.ro/event/festival-of-old-films/");
		jseExecutor =  (JavascriptExecutor)driver;
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(4000);
		driver.quit();
	}
	
	@Test
	public void openNewTab() throws InterruptedException {
		
		//WebDriverWait wait = new WebDriverWait(driver, 10); ==> Selenium 3
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName("iframe"))));
		WebElement iframe = driver.findElement(By.tagName("iframe"));
		jseExecutor.executeScript("window.scrollBy(0,900)");
		Actions action = new Actions(driver);
		action.moveToElement(iframe).perform();
		
		driver.switchTo().frame(iframe);
		driver.findElement(By.cssSelector("div[class='google-maps-link']")).click();
		List<String> browserTabs = new ArrayList<>(driver.getWindowHandles());
		System.out.println(browserTabs.size());
		System.out.println(browserTabs.get(0));
		System.out.println(browserTabs.get(1));
		/*Thread.sleep(5000);
		
		driver.switchTo().window(browserTabs.get(1));
		String pageTitle =  driver.getTitle();
		System.out.println("Page title :" + pageTitle);
		driver.findElement(By.id("searchboxinput")).clear();
		driver.findElement(By.id("searchboxinput")).sendKeys("Cluj");
		driver.findElement(By.id("searchbox-searchbutton")).click(); */
		
		
		WebDriver newTab = driver.switchTo().newWindow(WindowType.TAB);
		newTab.get("https://keyfood.ro");
		System.out.println(driver.getTitle());
		
	}
	
	
}
