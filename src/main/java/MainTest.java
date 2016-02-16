import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.String;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

/**
 * Created by QZen on 16/02/16.
 */
public class MainTest {
    private static WebDriver driver;
    private static String baseUrl;
    private static boolean acceptNextAlert = true;
    private static StringBuffer verificationErrors = new StringBuffer();

    public static void main(String args[]) throws Exception {
        //set up
        driver = new FirefoxDriver();
        baseUrl = "https://yandex.ru";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(baseUrl + "/");

        //tear down
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
