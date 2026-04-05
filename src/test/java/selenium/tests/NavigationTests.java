package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.NavigationPage;

@Headless
@Story(Namespaces.Stories.NAVIGATION)
public class NavigationTests extends BaseTest {
    private NavigationPage navigationPage;

    @BeforeEach
    public void openNavigationPage() {
        navigationPage = new HomePage().open().clickNavigation();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate the title on the Navigation page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Navigation example", navigationPage.getTitle());
    }

    @ParameterizedTest
    @MethodSource("selenium.utils.TestDataProvider#navigationPageTestData")
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate description and paginator states on the each page of Navigation")
    public void testNavigationPageStates(int pageNumber, boolean expectedPreviousState, boolean expectedNextState, String expectedDescription) throws InterruptedException {
        navigationPage.selectPage(pageNumber);
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedPreviousState, navigationPage.isPreviousDisabled(), "Incorrect previous state"),
                () -> Assertions.assertEquals(expectedNextState, navigationPage.isNextDisabled(), "Incorrect next state"),
                () -> Assertions.assertTrue(navigationPage.isPageHighlighted(pageNumber), "Incorrect page highlighted"),
                () -> Assertions.assertEquals(expectedDescription, navigationPage.getDescription(), "Incorrect description")
        );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.NORMAL)
    @DisplayName("Validate Next and Previous buttons behavior on the Navigation page")
    public void testNavigationPreviousNext() {
        navigationPage.clickNext();
        Assertions.assertTrue(navigationPage.isPageHighlighted(2), "Incorrect page highlighted");
        navigationPage.clickNext();
        Assertions.assertTrue(navigationPage.isPageHighlighted(3), "Incorrect page highlighted");
        navigationPage.clickPrevious();
        Assertions.assertTrue(navigationPage.isPageHighlighted(2), "Incorrect page highlighted");
        navigationPage.clickPrevious();
        Assertions.assertTrue(navigationPage.isPageHighlighted(1), "Incorrect page highlighted");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.NORMAL)
    @DisplayName("Validate ReturnToIndex button behavior on the Navigation page")
    public void testNavigationBackToIndex() {
        navigationPage.clickBackToIndex();
        Assertions.assertEquals(new HomePage().URL + "index.html", driver.getCurrentUrl(), "Incorrect url");
    }
}
