package demo.pageobject;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Amazonsearch {

	WebDriver driver;

	public Amazonsearch(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "twotabsearchtextbox")
	private WebElement search;

	@FindBy(css = "input[type='submit']")
	private WebElement submit;

	@FindBy(xpath = "//h2//a") // h2//a//span
	private List<WebElement> orders;

	@FindBy(xpath = "//a[contains(@aria-label,'Go to next page')]")
	private WebElement nextBTN;

	@FindBy(css = "h1[id='title'] span")
	private WebElement title;

	@FindBy(id = "buy-now-button")
	private WebElement buyNow;

	public void searchProduct(String searchText) throws InterruptedException {

		search.sendKeys(searchText);
		submit.click();
	}

	public void addProduct(String brandName) {

		boolean status = false;
		int count=1;
		while (!status) {

			for (int i = 0; i < orders.size(); i++) {
				
				if (orders.get(i).getText().contains(brandName)) {

					orders.get(i).click();
					status = true;
				}
			}

			nextBTN.click();
			count++;
			
			if(count==20) {
				break;
			}
		}
	}

	public String getProductTitle() {

		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		return title.getText();

	}

	public void buyTheProduct() {

		buyNow.click();
	}

}
