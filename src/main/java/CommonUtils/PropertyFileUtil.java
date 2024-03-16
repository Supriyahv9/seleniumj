package CommonUtils;

import java.io.FileInputStream;
import java.util.Properties;



public class PropertyFileUtil {

	public String getDatafromPropertyfile(String key) throws Throwable {
		
		FileInputStream fis = new FileInputStream("src\\test\\resources\\LoginCredential.properties");
		Properties p = new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
		
	}
}
