package page;

import static org.junit.Assert.assertTrue;

/**
 * Created by QZen on 02/04/16.
 */
public class PgMain extends PageBase {

    public void openPage() {
        log("Open page");
        getDriver().get(baseUrl);
        assertTrue(getDriver().getCurrentUrl().contains(baseUrl));
    }
}
