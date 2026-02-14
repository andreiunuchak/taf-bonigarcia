package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
    @DisplayName("Validate the title on the MouseOver page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Mouse over", mouseOverPage.getTitle());
    }

    @Test
    @DisplayName("Validate Compass note appearence")
    public void testCompassNoteAppearence() {
        mouseOverPage.hoverCompassImage();
        Assertions.assertTrue(mouseOverPage.isCompassNoteDisplayed());
    }

    @Test
    @DisplayName("Validate Calendar note appearence")
    public void testCalendarNoteAppearence() {
        mouseOverPage.hoverCalendarImage();
        Assertions.assertTrue(mouseOverPage.isCalendarNoteDisplayed());
    }
    @Test
    @DisplayName("Validate Award note appearence")
    public void testAwardNoteAppearence() {
        mouseOverPage.hoverAwardImage();
        Assertions.assertTrue(mouseOverPage.isAwardNoteDisplayed());
    }
    @Test
    @DisplayName("Validate Landscape note appearence")
    public void testLandscapeNoteAppearence() {
        mouseOverPage.hoverLandscapeImage();
        Assertions.assertTrue(mouseOverPage.isLandscapeNoteDisplayed());
    }
}
