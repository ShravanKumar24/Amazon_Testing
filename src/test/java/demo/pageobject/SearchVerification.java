package demo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class SearchVerification {

	WebDriver driver;

	public SearchVerification(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "twotabsearchtextbox")
	WebElement searchbox;

	@FindBy(id = "nav-search-submit-button")
	WebElement submit;

	@FindBy(xpath="//span[contains(@class,'a-size-medium')][2]")
	WebElement searchResult;

	SoftAssert sa = new SoftAssert();

	public String noItems(String text) {

		searchbox.click();
		searchbox.sendKeys(text);
		submit.click();
		String msg = searchResult.getText();
		System.out.println(msg);
		return msg;
	}
	
	public WebElement searchs() {
		
		return searchResult;
	}

}
