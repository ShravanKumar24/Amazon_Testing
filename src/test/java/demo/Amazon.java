package demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import demo.data.DataFile;
import demo.pageobject.Amazonsearch;
import demo.pageobject.LoginPage;
import demo.testcomponents.AmazonBase;

public class Amazon extends AmazonBase{

    @Test(dataProvider = "getValues")
	public void testUsingJsonData(HashMap<String,String> input) throws IOException, InterruptedException {
		
	    LoginPage login = website();
	    login.setUserName(input.get("email"));
	    login.setPassword(input.get("password"));	
	    
	    Amazonsearch as =new Amazonsearch(driver); 
		as.searchProduct(input.get("searchText"));
		as.addProduct(input.get("brandName"));
		String actTitle=as.getProductTitle();
		Assert.assertTrue(actTitle.startsWith(input.get("brandName")));
		as.buyTheProduct();
		

	}
	

	@DataProvider
	public Object[][] getValues() throws IOException {
		String pathFile="./src/test/java/demo/data/Amazondata.json";
		DataFile data=new DataFile();
		List<HashMap<String,String>>values=data.getData(pathFile);
		return new Object[][] {{values.get(0)},{values.get(1)}};
		
}
	
}
