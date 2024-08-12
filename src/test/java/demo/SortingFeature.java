package demo;

import org.testng.annotations.Test;

import demo.pageobject.SortingChecks;
import demo.testcomponents.AmazonBase;

public class SortingFeature extends AmazonBase {

	@Test
	public void sorts() throws Exception {

		String searchItem = "iphone";
		String sortType = "High to Low";

		SortingChecks sort = sorting();
		sort.sorting(searchItem, sortType);

	}
}
