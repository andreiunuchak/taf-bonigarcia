package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.DragAndDropPage;
import selenium.pages.HomePage;

@Headless
@Story(Namespaces.Stories.DRAG_AND_DROP)
public class DragAndDropTests extends BaseTest {

    private DragAndDropPage dragAndDropPage;

    @BeforeEach
    public void openPage() {
        dragAndDropPage = new HomePage(driver).open().clickDragAndDrop();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate the title on the DragAndDrop page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Drag and drop", dragAndDropPage.getTitle());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate drag and drop behavior")
    public void dragAndDropTest() {
        dragAndDropPage.dragAndDrop(dragAndDropPage.getDragElementCenterPoint(), dragAndDropPage.getDropElementCenterPoint());
        Assertions.assertEquals(dragAndDropPage.getDragElementCenterPoint(), dragAndDropPage.getDropElementCenterPoint());
    }
}
