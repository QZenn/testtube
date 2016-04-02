package page;

import static org.junit.Assert.assertTrue;

/**
 * Created by QZen on 02/04/16.
 */
public class PgMain extends PageBase {

    public void openMainPage() {
        log("Open main page");
        getDriver().get(baseUrl);
        assertTrue("Different URL was opened or redirect was performed. " +
                        "Expected URL: " + baseUrl,
                getDriver().getCurrentUrl().contains(baseUrl));
        log("Main page opened");
    }
}
