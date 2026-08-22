package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.DropdownMenuPage;
import selenium.pages.HomePage;

@Headless
@Story(Namespaces.Stories.DROPDOWN_MENU)
public class DropdownMenuTests extends BaseTest {
    private DropdownMenuPage dropdownMenuPage;

    @BeforeEach
    public void openDropDownMenuPage() {
        dropdownMenuPage = new HomePage(driver).open().clickDropDownMenu();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate the title on the DropdownMenu page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Dropdown menu", dropdownMenuPage.getTitle());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @Tag(Namespaces.Tags.FLAKY)
    @DisplayName("Validate all action buttons of the Left-Click Dropdown")
    public void testLeftClickDropdownMenu() {
        dropdownMenuPage.clickLeftClickDropdown()
                .clickLeftClickDropdown_Action()
                .clickLeftClickDropdown()
                .clickLeftClickDropdown_AnotherAction()
                .clickLeftClickDropdown()
                .clickLeftClickDropdown_SomethingElseHere()
                .clickLeftClickDropdown()
                .clickLeftClickDropdown_SeparatedLink();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate all action buttons of the Right-Click Dropdown")
    public void testRightClickDropdownMenu() {
        dropdownMenuPage.clickRightClickDropdown()
                .clickRightClickDropdown_Action()
                .clickRightClickDropdown()
                .clickRightClickDropdown_AnotherAction()
                .clickRightClickDropdown()
                .clickRightClickDropdown_SomethingElseHere()
                .clickRightClickDropdown()
                .clickRightClickDropdown_SeparatedLink();
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate all action buttons of the Double-Click Dropdown")
    public void testDoubleClickDropdownMenu() {
        dropdownMenuPage.clickDoubleClickDropdown()
                .clickDoubleClickDropdown_Action()
                .clickDoubleClickDropdown()
                .clickDoubleClickDropdown_AnotherAction()
                .clickDoubleClickDropdown()
                .clickDoubleClickDropdown_SomethingElseHere()
                .clickDoubleClickDropdown()
                .clickDoubleClickDropdown_SeparatedLink();
    }
}
