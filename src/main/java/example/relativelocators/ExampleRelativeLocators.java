package example.relativelocators;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import net.bytebuddy.implementation.bytecode.Duplication;
import utils.Driver;

public class ExampleRelativeLocators extends Driver{

	public WebDriver driver;
	public JavascriptExecutor jseExecutor; 
	String browser = System.getProperty("browser");
	
	//@Parameters({"browser"})
	@BeforeClass
	public void setUp() throws MalformedURLException {
		//driver = new ChromeDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver = initDriver(browser);
		driver.get("https://keybooks.ro/");
		jseExecutor =  (JavascriptExecutor)driver;
	}
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(4000);
		driver.quit();
	}

	@Test
	public void relativeLocators() throws InterruptedException {
		
		//toLeftOf
		//toRightof
		//below
		//above
		//near
		
		//WebElement price10_20 =  driver.findElement(By.xpath("//h5[(contains(text(), '$10.20'))]"));
		
		////h5[(contains(text(), '$15.99'))]
		
		//toLeftOf
		WebElement price10_20 = driver.findElement(RelativeLocator.with(By.xpath("//h5[(contains(text(), '$10.20'))]"))
				.toLeftOf(By.xpath("//h5[(contains(text(), '$15.99'))]")));
		jseExecutor.executeScript("arguments[0].setAttribute('style', 'background:orange; border:4px solid red;')", price10_20);
		Thread.sleep(3000);
		
		//toRightof
		WebElement firstPrice10_20 =  driver.findElement(RelativeLocator.with(By.xpath("//h5[(contains(text(), '$10.20'))]"))
				.toRightOf(By.xpath("//h5[(contains(text(), '$15.05'))]")));
		jseExecutor.executeScript("arguments[0].setAttribute('style', 'background:blue; border:4px solid red;')", firstPrice10_20);
		Thread.sleep(3000);

		//below
		WebElement textBook = driver.findElement(RelativeLocator.with(By.xpath("//a[(contains(text(), 'It’s a really strange story'))]"))
				.below(By.xpath("//img[contains(@src, 'book12')]")));
		jseExecutor.executeScript("arguments[0].setAttribute('style', 'background:green; border:4px solid red;')", textBook);
		Thread.sleep(3000);

		//above
		WebElement pictureBook = driver.findElement(RelativeLocator.with(By.xpath("//img[contains(@src, 'books7')]"))
				.above(By.xpath("//a[@href='storm']")));
		jseExecutor.executeScript("arguments[0].setAttribute('style', 'background:green; border:6px solid red;')", pictureBook);
		Thread.sleep(3000);
		
		//near
		WebElement authorName =  driver.findElement(RelativeLocator.with(By.xpath("//div[@class='wpb_wrapper']"))
				.near(textBook));
		jseExecutor.executeScript("arguments[0].setAttribute('style', 'background:yellow; border:6px solid red;')", authorName);
		Thread.sleep(3000);
		
		
		
	}
	
	
	
}
