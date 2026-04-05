package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.LoadingImagesPage;

@Headless
@Story(Namespaces.Stories.LOADING_IMAGES)
public class LoadingImagesTests extends BaseTest{

    private LoadingImagesPage loadingImagesPage;

    @BeforeEach
    public void openPage(){
        loadingImagesPage = new HomePage().open().clickLoadingImages();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate the title on the LoadingImages page")
    public void testLoadingImagesTitle() {
        Assertions.assertEquals("Loading images", loadingImagesPage.getTitle());
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.NORMAL)
    @DisplayName("Validate status texts on the LoadingImages page")
    public void testLoadingImagesStatus() {
        Assertions.assertAll(
                () -> Assertions.assertEquals("Please wait until the images are loaded...", loadingImagesPage.getStatusText()),
                () -> Assertions.assertTrue(loadingImagesPage.isStatusSpinnerDisplayed())
        );
        loadingImagesPage.waitForLoadingToComplete();
        Assertions.assertAll(
                () -> Assertions.assertEquals("Done!", loadingImagesPage.getStatusText()),
                () -> Assertions.assertFalse(loadingImagesPage.isStatusSpinnerDisplayed())
        );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate images appearance on the LoadingImages page")
    public void testLoadingImagesAppearance() {
        loadingImagesPage.waitForLoadingToComplete();
        Assertions.assertAll(
                () -> Assertions.assertTrue(loadingImagesPage.isCompassImageDisplayed()),
                () -> Assertions.assertTrue(loadingImagesPage.isCalendarImageDisplayed()),
                () -> Assertions.assertTrue(loadingImagesPage.isAwardImageDisplayed()),
                () -> Assertions.assertTrue(loadingImagesPage.isLandscapeImageDisplayed())
        );
    }


}
