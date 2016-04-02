/**
 * Created by QZen on 17/02/16.
 */

import org.apache.log4j.PropertyConfigurator;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.*;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class YoutubeTest {
    private WebDriver driver;
    private String baseUrl;
    final Logger log = LoggerFactory.getLogger(YoutubeTest.class);
    final String log4jConfPath = "src/test/resources/log4j.properties";

    @Before
    public void setUp() throws Exception {
        baseUrl = "https://youtube.com/";
        PropertyConfigurator.configure(log4jConfPath);
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("intl.accept_languages","fr");
        driver = new FirefoxDriver(profile);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    public void testYoutubeLogin() throws Exception {
        log.info("Open page");
        driver.get(baseUrl);

        log.info("Open language picker menu");
        WebElement langButton = driver.findElement(By.id("yt-picker-language-button"));
        langButton.click();

        log.info("Select English language");
        String engXpath = "//button[@value='en']";
        WebElement Eng = driver.findElement(By.xpath(engXpath));
        Eng.click();

        log.info("Proceed to sign in");
        WebElement loginButton = driver.findElement(By.cssSelector("button.yt-uix-button-primary"));
        loginButton.click();

        log.info("Enter email");
        WebElement login = driver.findElement(By.id("Email"));
        login.clear();
        login.sendKeys("randomtester23997");
        WebElement next = driver.findElement(By.id("next"));
        next.click();

        log.info("Enter password");
        WebElement password = driver.findElement(By.id("Passwd"));
        password.clear();
        String pswB64 = "cmFuZG9tcGFzc3dvcmQyMzk5Nw==";
        String psw = new String(Base64.decodeBase64(pswB64));
        password.sendKeys(psw);
        WebElement signIn = driver.findElement(By.id("signIn"));
        signIn.click();

        log.info("Check avatar as sign of successful logging in");
        WebElement avatar = driver.findElement(By.className("yt-masthead-user-icon"));
        assertTrue(avatar.isDisplayed());

        log.info("Go to upload file");
        WebElement uploadBtn = driver.findElement(By.linkText("Upload"));
        uploadBtn.click();

        log.info("Choose embedded file");
        WebElement input = driver.findElement(By.cssSelector("#upload-prompt-box > input[type=\"file\"]"));
        String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
        ((JavascriptExecutor) driver).executeScript(js, input);
        File videoFile = new File("src/test/resources/test.mp4");
        String pathToFile = videoFile.getCanonicalPath();
        input.sendKeys(pathToFile);

        log.info("Wait till loading and processing finished max 10m");
        WebElement upload_thumb_img = (new WebDriverWait(driver, 600))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("upload-thumb-img")));

        log.info("Set unique name for file");
        String videoNameStr = "quvayumac";
        WebElement videoName = driver.findElement(By.className("video-settings-title"));
        videoName.clear();
        videoName.sendKeys(videoNameStr);

        log.info("Publish video");
        WebElement publish = driver.findElement(By.className("save-changes-button"));
        publish.click();
        WebElement return_to_editing_button = (new WebDriverWait(driver, 600))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("return-to-editing-button")));

        int maxInteration = 10;
        int sleepMs = 10000;
        log.info("Wait for uploaded video become available via search for " + maxInteration*sleepMs + "ms");
        for ( int iteration = 0; iteration < maxInteration + 2; iteration++ )
        {
            Thread.sleep(sleepMs);
            WebElement searchField = driver.findElement(By.className("search-term"));
            searchField.clear();
            searchField.sendKeys(videoNameStr);
            WebElement searchBtn = driver.findElement(By.className("search-button"));
            searchBtn.click();
            List<WebElement> result = driver.findElements(By.className("yt-uix-tile-link"));
            if ( result.size() > 0) {
                if (result.get(0).getText().equals(videoNameStr)) {
                    break;
                }
            }
            if (iteration > maxInteration) {
                assertTrue("Video unavailable via search", false);
            }
        }

        log.info("Open video from search");
        WebElement videoLink = driver.findElement(By.linkText(videoNameStr));
        videoLink.click();

        log.info("Check video played");
        WebElement video = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//video")));
        assertTrue(video.isDisplayed());
        assertTrue(driver.findElement(By.id("eow-title")).getText().contains(videoNameStr));

        log.info("Delete uploaded video");
        driver.get("http://www.youtube.com/my_videos");
        (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#vm-video-list-container > div:nth-child(2) > p:nth-child(1) > span:nth-child(1)")));
        driver.findElement(By.cssSelector("#non-appbar-vm-video-actions-bar > div > span.yt-uix-form-input-checkbox-container.vm-select-all > input")).click();
        driver.findElement(By.cssSelector("#non-appbar-vm-video-actions-bar > div > div.yt-uix-menu.vm-video-action-button-selected")).click();
        driver.findElement(By.cssSelector(".non-appbar-action-menu-content > li:nth-child(8)")).click();
        driver.findElement(By.cssSelector(".vm-video-actions-delete-button-confirm")).click();
        WebElement successDelete = (new WebDriverWait(driver, 60))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#vm-general-notifs > div > div.yt-alert-content")));
        assertTrue("Success delete message is not displayed", successDelete.isDisplayed());
    }
}
