package Basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class ContactTest {
	PropertyFileUtil putil = new PropertyFileUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	ExcelUtil eutil=new ExcelUtil();
	JavaUtil jutil = new JavaUtil();
	
	@Test
	public void CreateContactTest() throws Throwable {
		WebDriver driver= new ChromeDriver();
		wutil.maximize(driver);
		wutil.implicitwait(driver);
		
		//To read data from Property File
		String URL = putil.getDatafromPropertyfile("Url");
		String USERNAME = putil.getDatafromPropertyfile("Username");
		String PASSWORD = putil.getDatafromPropertyfile("Password");
		
		//To read data from Excel File
		String FIRSTNAME = eutil.getDataFromExcelFile("Contacts", 0, 1);
		String LASTNAME = eutil.getDataFromExcelFile("Contacts", 1, 1);
		String ORGNAME = eutil.getDataFromExcelFile("Contacts", 2, 1);
		String GROUP = eutil.getDataFromExcelFile("Contacts", 3, 1);
		
		//To Launch application
		driver.get(URL);
		
		//Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("(//a[text()='Contacts'])[1]")).click();
		
		//Step5:Click on Create organization look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		
		//Step6:Enter firstname & lastname
		driver.findElement(By.name("firstname")).sendKeys(FIRSTNAME);
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		
		//To fail the testscript
	/*	String actualurl = driver.getCurrentUrl();
		String expectedurl="http://localhost:8888/pune/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing";
		Assert.assertEquals(actualurl, expectedurl);*/
		
		
		//Step7:Click on Group ratio button
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		wutil.handledropdown(dropdown, GROUP);
		
		
		//Step7:Click on  organization name + image
		driver.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		
		wutil.switchwindow(driver, "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=");
		
		
		//Step8:Enter Organization name in search tf
		driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);
		
		//Step9:Click on search now
		driver.findElement(By.name("search")).click();
		
		//Step9:Click on text
		driver.findElement(By.xpath("//a[text()='wipro']")).click();
		
		wutil.switchwindow(driver, "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");
		
		
				//Step8:Click on Save
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();

	
				
						//Step10:Logout the Application
				WebElement AdminImg = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wutil.mousehover(driver, AdminImg);
				
				driver.findElement(By.linkText("Sign Out")).click();
	}
}
