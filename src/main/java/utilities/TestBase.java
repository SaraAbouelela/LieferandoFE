package utilities;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.util.HashMap;
public class TestBase extends AbstractTestNGCucumberTests
{
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>(); //in case running using testNG parallel testing
    public static String browserType;
    ChromeOptions chromeOptions = new ChromeOptions();
    FirefoxOptions firefoxOptions = new FirefoxOptions();
    EdgeOptions edgeOptions = new EdgeOptions();

    //===================================Set Drivers Options and capabilities===========================
    //--------------------------------Driver Options------------------------------------

    public void setChromePreferences() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromeOptions.setCapability("prefs", chromePrefs);
        //CICD Integration Properties
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriverManager.chromedriver().clearDriverCache();
        WebDriverManager.chromedriver().setup();
    }

    public void getChromeDriver() {
        driver.set(new ChromeDriver(chromeOptions));
    }

    //------------------------------------------Firefox options---------------------------------
    public void setFirefoxCapabilities() {
        firefoxOptions.addArguments("--disable-dev-shm-usage");
        firefoxOptions.addArguments("--no-sandbox"); // Bypass OS security model
        WebDriverManager.firefoxdriver().clearDriverCache();
        WebDriverManager.firefoxdriver().setup();
    }

    public void getFirefoxDriver() {
        driver.set(new FirefoxDriver(firefoxOptions));
    }
    //-----------------------------------Edge Capabilities----------------------------------
    public void setEdgeCapabilities() {
        WebDriverManager.edgedriver().clearDriverCache();
        WebDriverManager.edgedriver().setup();
    }

    public void getEdgeDriver() {
        driver.set(new EdgeDriver(edgeOptions));
    }

    //==============================Set BrowserType================================

//    public void setBrowserType(@Optional("Chrome") String browser) {
////        ConfigUtil.loadTestConfigurations();
//////        String browser = ConfigUtil.BROWSER;
//        browserType.set(browser);
//        launchBrowser();}
    //=================================launch Browser================================
    @BeforeTest
    @Parameters("browserType")
    public void launchBrowser(@Optional("Chrome") String browser){
        if (browser.equals("Chrome")) {
            setChromePreferences();
            getChromeDriver();
        } else if (browser.equals("Firefox")) {
            setFirefoxCapabilities();
            getFirefoxDriver();
        } else if (browser.equals("Edge")) {
            setEdgeCapabilities();
            getEdgeDriver();
        }
    }

    public static WebDriver getDriver() // call this method to get the driver object and launch the browser
    {
        return driver.get();
    }

    public String getBrowserType()
    {
        return browserType;
    }

    //=================================Test Cases Annotations=============================
    @AfterTest
    public void quitDriver()
    {
        getDriver().quit();
    }
}
