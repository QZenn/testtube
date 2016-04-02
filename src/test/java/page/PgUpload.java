package page;

import data.Property;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static org.junit.Assert.assertTrue;

/**
 * Created by QZen on 02/04/16.
 */
public class PgUpload extends PageBase {

    public void selectEmbeddedFile() throws Exception {
        log("Choose embedded file");
        WebElement input = getDriver().findElement(By.cssSelector("#upload-prompt-box > input[type=\"file\"]"));
        String js = "arguments[0].style.height='auto'; arguments[0].style.visibility='visible';";
        ((JavascriptExecutor) getDriver()).executeScript(js, input);
        File videoFile = new File("src/test/resources/test.mp4");
        String pathToFile = videoFile.getCanonicalPath();
        input.sendKeys(pathToFile);
    }

    public void waitForLoadingAndProcessingFinished() {
        log("Wait till loading and processing finished max 10m");
        WebElement upload_thumb_img = (new WebDriverWait(getDriver(), 600))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("upload-thumb-img")));
    }

    public void setUniqueVideoName() {
        log("Set unique name for file");
        WebElement videoName = getDriver().findElement(By.className("video-settings-title"));
        videoName.clear();
        videoName.sendKeys(Property.VIDEO_NAME);
    }

    public void publishVideo() {
        log("Publish video");
        WebElement publish = getDriver().findElement(By.className("save-changes-button"));
        publish.click();
        WebElement return_to_editing_button = (new WebDriverWait(getDriver(), 600))
                .until(ExpectedConditions.visibilityOfElementLocated(By.className("return-to-editing-button")));

    }

    public void waitForVideoBecomeAvailableViaSearch() throws Exception{
        int maxInteration = 10;
        int sleepMs = 10000;
        log("Wait for uploaded video become available via search for " + maxInteration*sleepMs + "ms");
        for ( int iteration = 0; iteration < maxInteration + 2; iteration++ )
        {
            Thread.sleep(sleepMs);
            PgSearch pgSearch = new PgSearch();
            pgSearch.searchForText(Property.VIDEO_NAME);

            if (pgSearch.isVideoPresentInResults(Property.VIDEO_NAME)) {
                break;
            }

            if (iteration > maxInteration) {
                assertTrue("Video unavailable via search", false);
            }
        }
    }
}
