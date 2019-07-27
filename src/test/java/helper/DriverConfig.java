package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverConfig {
    public static String CURRENT_DIR = System.getProperty("user.dir");
    public static String CHROME_DRIVER_WINDOWS = CURRENT_DIR + "/driver/chromedriver.exe";
    public static String FIREFOX_DRIVER_WINDOWS = CURRENT_DIR + "/driver/geckodriver.exe";
    public static JavascriptExecutor js;
    public static WebDriver driver;
    public static FileInputStream fileInputStream;
    public static Properties props;
    public static String BROWSER;
    public static String URL;

    @BeforeSuite
    public void beforeSuite(){
    }

    @AfterSuite
    public void afterSuite(){
    }

    @BeforeTest
    public void beforeTest(){
        try {
            props = new Properties();
            fileInputStream =new FileInputStream(CURRENT_DIR + "/src/test/java/env.properties");
            props.load(fileInputStream);
            URL = props.getProperty("url");
            BROWSER = props.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest(){
        try {
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void setUp(){
        if(BROWSER.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_WINDOWS);
            driver = new ChromeDriver();
        }
        if(BROWSER.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver", FIREFOX_DRIVER_WINDOWS);
            driver = new FirefoxDriver();
        }
        if(driver!=null)
        {
            driver.get(URL);
            js = (JavascriptExecutor) driver;
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
    }

    @AfterMethod
    public void tearDown() {
        if(driver!=null) driver.quit();
    }
}
