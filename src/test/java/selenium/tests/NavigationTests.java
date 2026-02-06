package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.NavigationPage;

import java.util.stream.Stream;

@Headless
@Story(Namespaces.Stories.NAVIGATION)
public class NavigationTests extends BaseTest {
    private NavigationPage navigationPage;

    @BeforeEach
    public void openNavigationPage() {
        navigationPage = new HomePage().open().clickNavigation();
    }

    @Test
    @DisplayName("Validate the title on the Navigation page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Navigation example", navigationPage.getTitle());
    }

    @ParameterizedTest
    @MethodSource("pageProvider")
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
    @DisplayName("Validate ReturnToIndex button behavior on the WebFrom page")
    public void testNavigationBackToIndex() {
        navigationPage.clickBackToIndex();
        Assertions.assertEquals(new HomePage().URL + "index.html", driver.getCurrentUrl(), "Incorrect url");
    }

    private static Stream<Arguments> pageProvider() {
        return Stream.of(
                Arguments.of(1, true, false, "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."),
                Arguments.of(2, false, false, "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."),
                Arguments.of(3, false, true, "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
        );
    }
}
