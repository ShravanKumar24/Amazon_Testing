package demo;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import demo.pageobject.SearchVerification;
import demo.testcomponents.AmazonBase;

public class ErrorValidation extends AmazonBase {

	@Test
	public void searchingWithInvalidText() throws Exception {
		
		SoftAssert sa=new SoftAssert();
		SearchVerification search=searchVerify();
		String msg=search.noItems("asdafaugaeufh");
		sa.assertEquals(msg, "asdafaugaeufh");
		WebElement searchResult =search.searchs();
		highlightElement(searchResult);
		sa.assertAll();

		
	}
}
