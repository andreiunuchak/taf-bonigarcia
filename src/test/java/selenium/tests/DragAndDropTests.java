package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        dragAndDropPage = new HomePage().open().clickDragAndDrop();
    }

    @Test
    @DisplayName("Validate the title on the DragAndDrop page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Drag and drop", dragAndDropPage.getTitle());
    }

    @Test
    @DisplayName("Validate drag and drop behavior")
    public void dragAndDropTest() {
        dragAndDropPage.dragAndDrop(dragAndDropPage.getDragElementCenterPoint(), dragAndDropPage.getDropElementCenterPoint());
        Assertions.assertEquals(dragAndDropPage.getDragElementCenterPoint(), dragAndDropPage.getDropElementCenterPoint());
    }
}
