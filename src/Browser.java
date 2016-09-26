import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Browser {

	private static int whichBrowser = 2; //1 chrome, 2 ff, 3 edge, 4 IE 
	private static WebDriver driver;
	private static Wait<WebDriver> wait;

	public static void main(String[] args) {
		new Browser();
	}
	
	public Browser(){
		setupBrowserDriver();
		wait = new WebDriverWait(driver, 30);
        driver.get("http://www.google.com/");
        //visit_forums();
	}
	
	private void downloadDrivers(){
		
	}
	
	private void setupBrowserDriver(){
		switch (whichBrowser){
		case 1:
			//https://sites.google.com/a/chromium.org/chromedriver/downloads
			System.setProperty("webdriver.chrome.driver", "C:/Users/Milwy/githubStuff/SeleniumTest/drivers/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case 2:
			//https://github.com/mozilla/geckodriver/releases/tag/v0.10.0
			System.setProperty("webdriver.gecko.driver", "C:/Users/Milwy/githubStuff/SeleniumTest/drivers/geckodriver.exe");
			driver = new MarionetteDriver();
			//add gecko driver for 48+
			break;		
		case 3:
			//https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
			if(OSValidator.getOS().compareTo("windows 10")==0){
				System.out.println("I'm win10");
				System.setProperty("webdriver.edge.driver", "C:/Users/Milwy/githubStuff/SeleniumTest/drivers/MicrosoftWebDriver.exe");
				driver = new EdgeDriver();
			}
			break;		
		case 4:
			//http://www.seleniumhq.org/download/
			System.setProperty("webdriver.ie.driver", "C:/Users/Milwy/githubStuff/SeleniumTest/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		}
	}
	
	private static void visit_forums(){
		driver.get("http://boards.pbe.leagueoflegends.com/en/c/bugs");
		//class="discussion-list-item row  "
		System.out.println(driver.findElements(By.className("discussion-list-item row  ")).get(0).getText());
	}

}
