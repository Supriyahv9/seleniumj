package Basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import CommonUtils.ExcelUtil;
import CommonUtils.JavaUtil;
import CommonUtils.PropertyFileUtil;
import CommonUtils.WebDriverUtil;

public class OrganizationTest {
	
	public WebDriver driver;
	
	PropertyFileUtil putil = new PropertyFileUtil();
	WebDriverUtil wutil = new WebDriverUtil();
	ExcelUtil eutil=new ExcelUtil();
	JavaUtil jutil = new JavaUtil();

	@Test
	public void CreateOrganizationsTest() throws Throwable {
		
		/*WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();*/

		
		String BROWSER = putil.getDatafromPropertyfile("Browser");
		String URL = putil.getDatafromPropertyfile("Url");
		String USERNAME = putil.getDatafromPropertyfile("Username");
		String PASSWORD = putil.getDatafromPropertyfile("Password");
		
		
		if(BROWSER.equals("Chrome")) {
			 driver= new ChromeDriver();
		}else if(BROWSER.equals("Edge")) {
			driver=new EdgeDriver();
		}else {
			driver=new FirefoxDriver();
		}
		
		wutil.maximize(driver);
		wutil.implicitwait(driver);
		
		//To Launch application
		driver.get(URL);
		
		//Login to application
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		

		//To read data from Excel File
		String ORGNAME = eutil.getDataFromExcelFile("Organizations", 0, 1);
		String GROUP = eutil.getDataFromExcelFile("Organizations", 1, 1);
		
	
		
		//Step4:Click on Organizations Link
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step5:Click on Create organization look up image
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		
		//Step6:Create Organization
		driver.findElement(By.name("accountname")).sendKeys(ORGNAME+jutil.getRandamNumber());
		
		//Step7:Click on Group ratio button
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		Select s = new Select(dropdown);
		s.selectByVisibleText(GROUP);
		
		//Step8:Click on Save
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();

	
		
		
	}
	
	
}
