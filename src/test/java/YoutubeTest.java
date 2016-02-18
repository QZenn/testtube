/**
 * Created by QZen on 17/02/16.
 */

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.*;
import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class YoutubeTest {
    private WebDriver driver;
    private String baseUrl;

    @Before
    public void setUp() throws Exception {
        driver = new FirefoxDriver();
        baseUrl = "https://youtube.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void testYoutubeLogin() throws Exception {
        driver.get(baseUrl);
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
        File videoFile = new File("src/test/resources/test.mp4");
        String pathToFile = videoFile.getCanonicalPath();
        input.sendKeys(pathToFile);
        WebElement upload_thumb_img = (new WebDriverWait(driver, 600))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("upload-thumb-img")));
        WebElement publish = driver.findElement(By.className("save-changes-button"));
        publish.click();
        WebElement return_to_editing_button = (new WebDriverWait(driver, 600))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("return-to-editing-button")));
    }
}
