package testCases;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import commonFunctions.CommonFunctions;
import pageObjects.Login_Page_Objects;
import pageObjects.User_Role_Page_Objects;

public class Test_User_Role extends CommonFunctions {
	
	
	public void login() {
		
		//Initialize the pageobjects
		PageFactory.initElements(driver, Login_Page_Objects.class);

		Login_Page_Objects.userName.sendKeys(properties.getProperty("username"));
		Login_Page_Objects.password.sendKeys(properties.getProperty("password"));
		Login_Page_Objects.submitBtn.click();

	}
	
	
	public void moveToUser() {
		Actions actions = new Actions(driver);
		actions.moveToElement(User_Role_Page_Objects.adminLink).perform();;
		actions.moveToElement(User_Role_Page_Objects.userManagementLink).perform();;
		actions.moveToElement(User_Role_Page_Objects.usersLink).click().build().perform();
		//actions.click().build().perform();
		
		
	}
	
	public void selectUser() {
		Select select = new Select(User_Role_Page_Objects.userRole);
		select.selectByIndex(1);
	}
	
	public void selectStatus() {
		Select select = new Select(User_Role_Page_Objects.userStatus);
		select.selectByIndex(1);
	}
	
	@Test
	public void checkUserRole() {
		//login();
		
		//Initialize the pageobjects
		
		PageFactory.initElements(driver, User_Role_Page_Objects.class);
		moveToUser();
		selectUser();
		selectStatus();
		User_Role_Page_Objects.searchBtn.click();
		String actualRole = User_Role_Page_Objects.userRoleValue.getText();
		String actualStatus = User_Role_Page_Objects.userStatusValue.getText();
		
		Assert.assertEquals(actualRole, "Admin");
		Assert.assertEquals(actualStatus, "Enabled");
		
		
	}

}
