package demo.pageobject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SortingChecks {

WebDriver driver;
	
	
	public SortingChecks(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="twotabsearchtextbox")
	WebElement searchBar;
	
	@FindBy(id="nav-search-submit-button")
	WebElement submit;
	
	@FindBy(id="a-autoid-0-announce")
     WebElement sort;
	
	@FindBy(css=".a-popover-inner li")
	List<WebElement> sortText;
	
	public void sorting(String searchItem, String sortType) {
		
		searchBar.click();
		searchBar.sendKeys(searchItem);
		submit.click();
		sort.click();
		
		for(int i=0;i<sortText.size();i++) {
			
			if(sortText.get(i).getText().contains(sortType)) {
				
				sortText.get(i).click();
			}
		}
	}
	
}
