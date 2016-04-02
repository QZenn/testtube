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
public class PgVideoManager extends PageBase {

    public void openManagerAndDeleteVideo() {
        log("Delete uploaded video");
        getDriver().get(Property.BASE_URL + "my_videos");
        (new WebDriverWait(getDriver(), 60))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#vm-video-list-container > div:nth-child(2) > p:nth-child(1) > span:nth-child(1)")));
        getDriver().findElement(By.cssSelector("#non-appbar-vm-video-actions-bar > div > span.yt-uix-form-input-checkbox-container.vm-select-all > input")).click();
        getDriver().findElement(By.cssSelector("#non-appbar-vm-video-actions-bar > div > div.yt-uix-menu.vm-video-action-button-selected")).click();
        getDriver().findElement(By.cssSelector(".non-appbar-action-menu-content > li:nth-child(8)")).click();
        getDriver().findElement(By.cssSelector(".vm-video-actions-delete-button-confirm")).click();
        WebElement successDelete = (new WebDriverWait(getDriver(), 60))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#vm-general-notifs > div > div.yt-alert-content")));
        assertTrue("Success delete message is not displayed", successDelete.isDisplayed());
    }
}
