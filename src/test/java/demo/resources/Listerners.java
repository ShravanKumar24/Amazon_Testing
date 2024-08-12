package demo.resources;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import demo.reports.Reports;
import demo.testcomponents.AmazonBase;


public class Listerners extends AmazonBase implements ITestListener {

	ExtentTest test;
	Reports repo=new Reports();
    ExtentReports extent=repo.getReports();
    ThreadLocal<ExtentTest> safe=new ThreadLocal<ExtentTest>(); //Used when test run parallel
    
	  public  void onTestStart(ITestResult result) {
		  
		  test=extent.createTest(result.getMethod().getMethodName());
		  safe.set(test);
		  }
	  
	  
	public  void onTestSuccess(ITestResult result) {
	  
		safe.get().log(Status.PASS, "Test Passed");
	  }
	
	
	public  void onTestFailure(ITestResult result) {
		
		safe.get().fail(result.getThrowable());
		String filepath = null;
		
		try {
			
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
			filepath=getScreenshot(result.getMethod().getMethodName(),driver);
			safe.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		
	  }

	public  void onFinish(ITestContext context) {
	  
		extent.flush();
	  }
}
