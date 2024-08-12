package demo.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	private WebDriver driver;

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "nav-signin-tooltip")
	private WebElement login;

	@FindBy(id = "continue")
	private WebElement contBtn;

	@FindBy(css = "input[type='email']")
	private WebElement user;

	@FindBy(id = "continue")
	private WebElement next;

	@FindBy(css = "input[type='password']")
	private WebElement password;

	@FindBy(id = "signInSubmit")
	private WebElement signIn;
	
	
	public void setUserName(String email) {
		
		login.click();
		user.sendKeys(email);
		contBtn.click();
	}
	
	public void setPassword(String passwords) {
		
		password.sendKeys(passwords);
		signIn.click();

	}
	
	

}
