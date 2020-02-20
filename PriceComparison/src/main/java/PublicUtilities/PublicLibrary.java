package PublicUtilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ApplicationUtilities.ApplicationObjects;

import java.util.concurrent.TimeUnit;

public class PublicLibrary {
    public static WebDriver driver;
    public void setUp(String browser)  {
        if(browser.trim().equalsIgnoreCase(Constant.chromeBrowser))
        {
            System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
            driver=new ChromeDriver();
        }
        else if(browser.trim().equalsIgnoreCase(Constant.firfoxBrowser))
        {
            System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
            driver=new FirefoxDriver();
        }
        else
        {
            System.out.println("Wrong Browser Type");
        }
    }
    public void maximizeBrowser()
    {
        driver.manage().window().maximize();
    }
    public void quitBrowser()
    {
        driver.quit();
    }
    public void navigateToUrl(String url)
    {
        implicitWait(Constant.shortWait);
        driver.get(url);
    }
    public void implicitWait(long waitTime)
    {
        driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);
    }
    public void setValue(By locator,String element)
    {
    	driver.findElement(locator).sendKeys(element);
    }
    public void clickElement(By locator)
    {
    	driver.findElement(locator).click();
    }
}
