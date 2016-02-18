import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.lang.String;
import java.util.concurrent.TimeUnit;

/**
 * Created by QZen on 16/02/16.
 */
public class MainTest {

    public static void main(String args[]) {
        //set up
        WebDriver driver = new FirefoxDriver();
        String baseUrl = "https://ya.ru";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(baseUrl + "/");
        WebElement searchTestField = driver.findElement(By.name("text"));
        searchTestField.click();
        searchTestField.clear();
        searchTestField.sendKeys("test");
        WebElement searchBotton = driver.findElement(By.className("button"));
        searchBotton.click();
        String title = driver.getTitle();
        System.out.println(title);

        //tear down
        driver.quit();
    }
}
