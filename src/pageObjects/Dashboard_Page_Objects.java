package pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Dashboard_Page_Objects {
	
	
	@FindBy(xpath="//*[@id='task-list-group-panel-container']//following::td/a")
	public static List<WebElement> pendingLeaveRequest;

}
