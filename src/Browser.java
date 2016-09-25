import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

	static int whichBrowser = 1; //1 chrome, 2 ff, 3 edge

	static WebDriver driver;
	static Wait<WebDriver> wait;


	public static void main(String[] args) {
		switch (whichBrowser){
		case 1:
			System.setProperty("webdriver.chrome.driver", "C:/Users/Milwy/githubStuff/SeleniumTest/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
			
		case 2:
			driver = new FirefoxDriver();
			//add gecko driver for 48+
			break;
			
		case 3:
			
			break;
		}
		
		wait = new WebDriverWait(driver, 30);
        driver.get("http://www.google.com/");
        visit_forums();
	}
	
	private static void visit_forums(){
		driver.get("http://boards.pbe.leagueoflegends.com/en/c/bugs");
		//class="discussion-list-item row  "
		System.out.println(driver.findElements(By.className("discussion-list-item row  ")).get(0).getText());
	}

}
