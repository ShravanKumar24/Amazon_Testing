package demo.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

import demo.pageobject.Amazonsearch;
import demo.pageobject.LoginPage;
import demo.pageobject.SearchVerification;
import demo.pageobject.SortingChecks;
import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonBase {

	protected WebDriver driver;
	private String url;
	private String[] args = { "--remote-allow-origins=*", "--incognito", "--start-maximized" };

	public WebDriver driverIntialize() throws IOException {
		String filepath = "./src/test/java/demo/resources/GlobalData.properties";
		FileInputStream fis = new FileInputStream(filepath);
		Properties prop = new Properties();
		prop.load(fis);
		String browser = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
		url = System.getProperty("url") != null ? System.getProperty("url") : prop.getProperty("url");

		// Use for Chrome browser
		if (browser.equals("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.addArguments(args);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}

		// Use for firefox browser
		else if (browser.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments(args);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(options);

		}

		// Use for Edge browser
		else if (browser.equals("edge")) {

			EdgeOptions options = new EdgeOptions();
			options.addArguments(args);
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver(options);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@AfterMethod
	public void tearDown() {

		driver.quit();
	}

	public LoginPage website() throws IOException {
		driver = driverIntialize();
		driver.get(url);
		LoginPage login = new LoginPage(driver);
		return login;
	}

	public SearchVerification searchVerify() throws Exception {

		driver = driverIntialize();
		driver.get(url);
		SearchVerification search = new SearchVerification(driver);
		return search;
	}

	public SortingChecks sorting() throws Exception {

		driver = driverIntialize();
		driver.get(url);
		SortingChecks sort = new SortingChecks(driver);
		return sort;
	}

	public String getScreenshot(String testName, WebDriver driver) throws Exception {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File fs = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "//reports//" + testName + ".png");
		FileUtils.copyFile(fs, destFile);
		System.out.println("Screenshot taken");
		return System.getProperty("user.dir") + "//reports//" + testName + ".png";

	}

	public void highlightElement(WebElement element) throws InterruptedException {

		String originalStyle = element.getAttribute("style");
		String style = "border: 2px solid red;";
		// Use the JavascriptExecutor to apply the new style to the element
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				style);

		// Wait for a brief moment to see the highlight
		Thread.sleep(2000);

		// Restore the original style of the element
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				originalStyle);
	}

}
