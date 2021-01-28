package testCases;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonFunctions.CommonFunctions;
import pageObjects.Dashboard_Page_Objects;
import pageObjects.Login_Page_Objects;

public class Test_Pending_Leave_Request extends CommonFunctions {
	
	int noOfLeaveRequest=0;
	
	Logger logger = Logger.getLogger(Test_Pending_Leave_Request.class);

	
	public void login() {
		
		
		logger.info("Logging into Appln");
		//Initialize the pageobjects
		PageFactory.initElements(driver, Login_Page_Objects.class);

		Login_Page_Objects.userName.sendKeys(properties.getProperty("username"));
		Login_Page_Objects.password.sendKeys(properties.getProperty("password"));
		Login_Page_Objects.submitBtn.click();

	}
	
	
	public void getPendingLeaveRequest() {
		PageFactory.initElements(driver, Dashboard_Page_Objects.class);

		List<WebElement> leaveRequest = Dashboard_Page_Objects.pendingLeaveRequest;
		
		noOfLeaveRequest=leaveRequest.size();
		for (WebElement webElement : leaveRequest) {
			System.out.println(webElement.getText().trim());
		}
	}

	@Test
	public void verifyPendingLeaveRequest() {

		login();
		logger.info("Get pending leave request");
		getPendingLeaveRequest();
		logger.info("Doing Assertions");
		Assert.assertEquals(noOfLeaveRequest, 11);
		logger.info("End of Test_pending_Leave_Request Tc's");

	}

}
