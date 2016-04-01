package page;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by QZen on 01/04/16.
 */
public class PageBase {
    private static WebDriver driver;
    final protected String baseUrl = "https://youtube.com/";
    final protected Logger log = LoggerFactory.getLogger(PageBase.class);
    final String log4jConfPath = "src/test/resources/log4j.properties";

    public void setUp() throws Exception {
        PropertyConfigurator.configure(log4jConfPath);
        getDriver();
    }

    public void tearDown() throws Exception {
        getDriver().quit();
    }

    public static WebDriver getDriver() {
        synchronized (PageBase.class){
            if (driver == null) {
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("intl.accept_languages","fr");
                driver = new FirefoxDriver(profile);
                driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                driver.manage().deleteAllCookies();
                driver.manage().window().maximize();
            }
        }
        return driver;
    }
}
