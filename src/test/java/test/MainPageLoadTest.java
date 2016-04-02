package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.PageBase;

import static org.junit.Assert.assertTrue;

/**
 * Created by QZen on 02/04/16.
 */
public class MainPageLoadTest extends PageBase {

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setLogTag(MainPageLoadTest.class);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void mainPageLoadTest() throws Exception {
        log("Open page");
        getDriver().get(baseUrl);
        assertTrue(getDriver().getCurrentUrl().contains(baseUrl));
    }
}
