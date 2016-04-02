package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by QZen on 02/04/16.
 */
public class PgSearch extends PageBase {

    public void searchForText(String text) {
        log("Search for video. Request is: " + text);
        WebElement searchField = getDriver().findElement(By.className("search-term"));
        searchField.clear();
        searchField.sendKeys(text);
        WebElement searchBtn = getDriver().findElement(By.className("search-button"));
        searchBtn.click();
    }

    public Boolean isVideoPresentInResults(String name) {
        Boolean isPresent = false;
        List<WebElement> result = getDriver().findElements(By.className("yt-uix-tile-link"));
        if ( result.size() > 0) {
            if (result.get(0).getText().equals(name)) {
                isPresent = true;
            }
        }
        return isPresent;
    }
}
