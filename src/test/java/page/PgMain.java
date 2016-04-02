package page;

import data.Property;

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
}
