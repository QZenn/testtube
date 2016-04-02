package page;

import data.Property;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

/**
 * Created by QZen on 02/04/16.
 */
public class PgMain extends PageBase {

    public void openMainPage() {
        log("Open main page");
        getDriver().get(Property.BASE_URL);
        assertTrue("Different URL was opened or redirect was performed. " +
                        "Expected URL: " + Property.BASE_URL,
                getDriver().getCurrentUrl().contains(Property.BASE_URL));
        log("Main page opened");
    }

    public void openLangMenu() {
        log("Open language picker menu");
        WebElement langButton = getDriver().findElement(By.id("yt-picker-language-button"));
        langButton.click();
    }

    public void selectEnglishLangFromMenu() {
        log("Select English language");
        String engXpath = "//button[@value='en']";
        WebElement Eng = getDriver().findElement(By.xpath(engXpath));
        Eng.click();
    }

    public void clickOnSignIn() {
        log("Proceed to sign in");
        WebElement loginButton = getDriver().findElement(By.cssSelector("button.yt-uix-button-primary"));
        loginButton.click();
    }

    public void goToUploadVideoPage() {
        log("Go to upload file");
        WebElement uploadBtn = getDriver().findElement(By.linkText("Upload"));
        uploadBtn.click();
    }

    public void openVideoFromSearch() {
        log("Open video from search");
        WebElement videoLink = getDriver().findElement(By.linkText(Property.VIDEO_NAME));
        videoLink.click();
    }
}
