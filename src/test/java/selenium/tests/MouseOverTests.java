package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.MouseOverPage;

@Headless
@Story(Namespaces.Stories.MOUSE_OVER)
public class MouseOverTests extends BaseTest{
    private MouseOverPage mouseOverPage;

    @BeforeEach
    public void openPage(){
        mouseOverPage = new HomePage().open().clickMouseOver();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate the title on the MouseOver page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Mouse over", mouseOverPage.getTitle());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Compass note appearence")
    public void testCompassNoteAppearence() {
        mouseOverPage.hoverCompassImage();
        Assertions.assertTrue(mouseOverPage.isCompassNoteDisplayed());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Calendar note appearence")
    public void testCalendarNoteAppearence() {
        mouseOverPage.hoverCalendarImage();
        Assertions.assertTrue(mouseOverPage.isCalendarNoteDisplayed());
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Award note appearence")
    public void testAwardNoteAppearence() {
        mouseOverPage.hoverAwardImage();
        Assertions.assertTrue(mouseOverPage.isAwardNoteDisplayed());
    }
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Landscape note appearence")
    public void testLandscapeNoteAppearence() {
        mouseOverPage.hoverLandscapeImage();
        Assertions.assertTrue(mouseOverPage.isLandscapeNoteDisplayed());
    }
}
