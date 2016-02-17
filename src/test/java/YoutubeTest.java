/**
 * Created by QZen on 17/02/16.
 */

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class YoutubeTest {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://ya.ru";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @Test
    public void testYaru() throws Exception {
        driver.get(baseUrl + "/");
        WebElement searchTestField = driver.findElement(By.name("text"));
        searchTestField.click();
        searchTestField.clear();
        searchTestField.sendKeys("test");
        WebElement searchBotton = driver.findElement(By.className("button"));
        searchBotton.click();
        String title = driver.getTitle();
        System.out.println(title);
        assertTrue(title.contains("test"));
    }
}
