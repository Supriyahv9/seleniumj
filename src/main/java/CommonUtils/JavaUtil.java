package CommonUtils;

import java.util.Random;

public class JavaUtil {
	
	public int getRandamNumber() {
		
		Random r =new Random();
		int ran = r.nextInt(1000);
		return ran;
		
		
	}

}
