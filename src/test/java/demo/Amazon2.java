package demo;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import demo.data.excel.DataDriven;
import demo.pageobject.Amazonsearch;
import demo.pageobject.LoginPage;
import demo.testcomponents.AmazonBase;

public class Amazon2 extends AmazonBase {

	@Test(dataProvider = "getData")
	public void testUsingExcelData(String email, String password, String searchtext, String brandname) throws Exception {

		LoginPage login = website();
		login.setUserName(email);
		login.setPassword(password);

		Amazonsearch as = new Amazonsearch(driver);
		as.searchProduct(searchtext);
		as.addProduct(brandname);
		String actTitle = as.getProductTitle();
		Assert.assertTrue(actTitle.startsWith(brandname));
		as.buyTheProduct();

	}

	@DataProvider
	public String[][] getData() throws Exception {
		String pathFile = "./data/data.xlsx";
		String sheetName = "testdata";
		DataDriven datadriven = new DataDriven();
		String[][] data = datadriven.getExcelData(pathFile, sheetName);
		return data;

	}

}
