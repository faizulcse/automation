package helper;

//import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    public static WebDriverWait webDriverWait;
    public static FileInputStream fileInputStream;
    public static Properties props;
    public static String BROWSER;
    public static int wait;
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
            wait = Integer.parseInt(props.getProperty("waitTime"));
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
            webDriverWait = new WebDriverWait(driver, wait);
//            Dimension d = new Dimension(521,628);
//            driver.manage().window().setSize(d);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
        }
    }

    @AfterMethod
    public void tearDown() {
        if(driver!=null) driver.quit();
    }

    public static void scrollUp(){
        int i = 1;
        while (i <= 100) {
            js.executeScript("window.scrollBy(0, -1)", "");
            waitFor(1.0);
            i++;
        }
    }

    public static void scrollUp(int count){
        int i = 0;
        while (i < count * 100) {
            js.executeScript("window.scrollBy(0, -1)", "");
            waitFor(1.0);
            i++;
        }
    }

    public static void scrollDown(){
        int i = 0;
        while (i < 100) {
            js.executeScript("window.scrollBy(0, 1)", "");
            waitFor(1.0);
            i++;
        }
    }

    public static void scrollDown(int count){
        int i = 0;
        while (i < 100 * count) {
            js.executeScript("window.scrollBy(0, 1)", "");
            waitFor(1.0);
            i++;
        }
    }

    public static void scrollLeft(){
        int i = 0;
        while (i < 100) {
            js.executeScript("window.scrollBy(-1, 0)", "");
            waitFor(1.0);
            i++;
        }
    }

    public static void scrollLeft(int count){
        int i = 0;
        while (i < 100 * count) {
            js.executeScript("window.scrollBy(-1, 0)", "");
            waitFor(1.0);
            i++;
        }
    }

    public static void scrollRight(){
        int i = 0;
        while (i < 100) {
            js.executeScript("window.scrollBy(1, 0)", "");
            waitFor(1.0);
            i++;
        }
    }

    public static void scrollRight(int count){
        int i = 0;
        while (i < 100 * count) {
            js.executeScript("window.scrollBy(1, 0)", "");
            waitFor(1.0);
            i++;
        }
    }

    public static void waitFor(double milliSecond){
        try {
            Thread.sleep((long) milliSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void waitFor(int second){
        try {
            Thread.sleep((int) second*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
