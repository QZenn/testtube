/**
 * Created by QZen on 17/02/16.
 */

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.codec.binary.Base64;

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
        driver.manage().window().maximize();
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

    @Test
    public void testYoutubeLogin() throws Exception {
        driver.manage().deleteAllCookies();
        driver.get("https://youtube.com/");
        WebElement langButton = driver.findElement(By.id("yt-picker-language-button"));
        langButton.click();
        String engXpath = "//button[@value='en']";
        WebElement Eng = driver.findElement(By.xpath(engXpath));
        Eng.click();
        WebElement loginButton = driver.findElement(By.cssSelector("button.yt-uix-button-primary"));
        loginButton.click();
        WebElement login = driver.findElement(By.id("Email"));
        login.clear();
        login.sendKeys("randomtester23997");
        WebElement next = driver.findElement(By.id("next"));
        next.click();
        WebElement password = driver.findElement(By.id("Passwd"));
        password.clear();
        String pswB64 = "cmFuZG9tcGFzc3dvcmQyMzk5Nw==";
        String psw = new String(Base64.decodeBase64(pswB64));
        password.sendKeys(psw);
        System.out.println();
        WebElement signIn = driver.findElement(By.id("signIn"));
        signIn.click();
        WebElement avatar = driver.findElement(By.className("yt-masthead-user-icon"));
        assertTrue(avatar.isDisplayed());
        WebElement uploadBtn = driver.findElement(By.linkText("Upload"));
        uploadBtn.click();
        WebElement input = driver.findElement(By.cssSelector("#upload-prompt-box > input[type=\"file\"]"));
        String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
        ((JavascriptExecutor) driver).executeScript(js, input);
        input.sendKeys("absoutePathToFile");
    }
}
