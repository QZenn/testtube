package page;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

/**
 * Created by QZen on 02/04/16.
 */
public class PgSignIn extends PageBase {

    public void enterEmailAndProceed(){
        log("Enter email");
        WebElement login = getDriver().findElement(By.id("Email"));
        login.clear();
        login.sendKeys("randomtester23997");
        WebElement next = getDriver().findElement(By.id("next"));
        next.click();
    }

    public void enterPasswordAndProceed() {
        log("Enter password");
        WebElement password = getDriver().findElement(By.id("Passwd"));
        password.clear();
        String pswB64 = "cmFuZG9tcGFzc3dvcmQyMzk5Nw==";
        String psw = new String(Base64.decodeBase64(pswB64));
        password.sendKeys(psw);
        WebElement signIn = getDriver().findElement(By.id("signIn"));
        signIn.click();
    }

    public void checkSuccessfulSigningIn() {
        log("Check avatar as sign of successful logging in");
        WebElement avatar = getDriver().findElement(By.className("yt-masthead-user-icon"));
        assertTrue(avatar.isDisplayed());
    }
}
