import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

@SuppressWarnings("deprecation")
public class Browser {

	private static int whichBrowser = 1; //1 chrome, 2 ff, 3 edge, 4 IE 
	private static WebDriver driver;
	private static Wait<WebDriver> wait;

	public static void main(String[] args) {
		new Browser();
	}
	
	public Browser(){
		//downloadDrivers("C:/Users/Milwy/Desktop/");
		setupBrowserDriver();
		
		wait = new WebDriverWait(driver, 30);
        driver.get("http://www.google.com/");
        visit_forums();
	}
	
	private void downloadDrivers(String directory){
		if(directory.compareTo("")==0) directory="C:/Users/Milwy/Desktop/";
		try {
			
			FileUtils.copyURLToFile(new URL("https://chromedriver.storage.googleapis.com/2.24/chromedriver_win32.zip"), new File(directory+"chromedriver_win32.zip"));
			FileUtils.copyURLToFile(new URL("https://github.com/mozilla/geckodriver/releases/download/v0.10.0/geckodriver-v0.10.0-win64.zip"), new File(directory+"geckodriver-v0.10.0-win64.zip"));
			FileUtils.copyURLToFile(new URL("http://selenium-release.storage.googleapis.com/2.53/IEDriverServer_Win32_2.53.1.zip"), new File(directory+"IEDriverServer_Win32_2.53.1.zip"));

			// TODO EDGEDOWNLOAD
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		//TODO UNZIP FILES INTO JAVA DIR
		System.out.println("Done prinnting");
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
			//http://selenium-release.storage.googleapis.com/2.53/IEDriverServer_Win32_2.53.1.zip
			System.setProperty("webdriver.ie.driver", "C:/Users/Milwy/githubStuff/SeleniumTest/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			break;
		}
	}
	
	private static void visit_forums(){
		driver.get("http://boards.pbe.leagueoflegends.com/en/c/bugs");
		List <WebElement> rootElement = driver.findElements(By.xpath("//tbody[@id='discussion-list']//tr[@class='discussion-list-item row  ']"));
		
		ArrayList<String> titles= new ArrayList<String>();
		for(int i=0; i<rootElement.size();i++){
			WebElement titleText= rootElement.get(i).findElement(By.xpath(".//td[@class='title']//div[@class='discussion-title opaque']//a[@class='title-link']"));
			titles.add(titleText.getText());
			System.out.println(titleText.getText());
		}
		
		//WebElement test2 = rootElement.get(46).findElement(By.xpath(".//td[@class='title']//div[@class='discussion-title opaque']//a[@class='title-link']"));
		//System.out.println(test2.getText());
	}

}
