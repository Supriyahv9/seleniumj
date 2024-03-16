package CommonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtil {
	


	//Pass WebDriver driver as parameter
	public void maximize(WebDriver driver) {
		
		driver.manage().window().maximize();
	}
	
	public void implicitwait(WebDriver driver) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	public void mousehover(WebDriver driver,WebElement element) {
		
		Actions a = new Actions(driver);
		a.moveToElement(element);
		a.perform();
	}
	
public void handledropdown(WebElement element,String targetedelement) {
		
		Select s = new Select(element);
		s.selectByVisibleText(targetedelement);
	}

public void switchwindow(WebDriver driver,String url) {
	
	Set<String> allwindowIds = driver.getWindowHandles();
	
	//iterate through indivitual Ids
	for(String winID : allwindowIds) {
		
		//Switch to each ID & capture the title
		String currenturl = driver.switchTo().window(winID).getCurrentUrl();
	
		
		//Comapre the title with required referance
		if(currenturl.contains(url)) {
			break;
			
		}
	}
}
	
	public String screenshot(WebDriver driver,String ScreenshotName) throws IOException {
		
		TakesScreenshot ts =(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst= new File("./Screenshot/"+ScreenshotName+".png");
		FileUtils.copyFile(src, dst);
		return dst.getAbsolutePath();
		
	}

}


