package page;

import data.Property;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

/**
 * Created by QZen on 02/04/16.
 */
public class PgPlayer extends PageBase {

    public void checkVideoIsPlayed() {
        log("Check video played");
        WebElement video = (new WebDriverWait(getDriver(), 60))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//video")));
        assertTrue(video.isDisplayed());
        assertTrue(getDriver().findElement(By.id("eow-title")).getText().contains(Property.VIDEO_NAME));
    }
}
