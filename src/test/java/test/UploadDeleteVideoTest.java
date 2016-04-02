package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page.*;

/**
 * Created by QZen on 02/04/16.
 */
public class UploadDeleteVideoTest extends PageBase {

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setLogTag(UploadDeleteVideoTest.class);
    }

    @After
    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void uploadAndDeleteVideoYoutubeTest() throws Exception {
        PgMain pgMain = new PgMain();
        pgMain.openMainPage();
        pgMain.openLangMenu();
        pgMain.selectEnglishLangFromMenu();
        pgMain.clickOnSignIn();

        PgSignIn pgSignIn = new PgSignIn();
        pgSignIn.enterEmailAndProceed();
        pgSignIn.enterPasswordAndProceed();
        pgSignIn.checkSuccessfulSigningIn();
        pgMain.goToUploadVideoPage();

        PgUpload pgUpload = new PgUpload();
        pgUpload.selectEmbeddedFile();
        pgUpload.waitForLoadingAndProcessingFinished();
        pgUpload.setUniqueVideoName();
        pgUpload.publishVideo();
        pgUpload.waitForVideoBecomeAvailableViaSearch();
        pgMain.openVideoFromSearch();

        PgPlayer pgPlayer = new PgPlayer();
        pgPlayer.checkVideoIsPlayed();

        PgVideoManager pgVideoManager = new PgVideoManager();
        pgVideoManager.openManagerAndDeleteVideo();
    }
}
