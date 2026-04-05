package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.interactions.Actions;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.InfiniteScrollPage;

@Headless
@Story(Namespaces.Stories.INFINITE_SCROLL)
public class InfiniteScrollTests extends BaseTest {
    private InfiniteScrollPage infiniteScrollPage;

    @BeforeEach
    public void openPage() {
        infiniteScrollPage = new HomePage().open().clickInfiniteScroll();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate Title on the InfiniteScroll page")
    public void testHomeTitle() {
        Assertions.assertEquals("Infinite scroll", infiniteScrollPage.getTitle());
    }

    @ParameterizedTest
    @MethodSource("selenium.utils.TestDataProvider#paragraphsTestData")
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.MEDIUM)
    @DisplayName("Validate paragraph text on the InfiniteScroll page")
    public void testInfinitePageParagraphsTexts(int id, String expectedText) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(20, infiniteScrollPage.getParagraphsAmount()),
                () -> Assertions.assertEquals(expectedText, infiniteScrollPage.getParagraphText(id))
        );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate scroll on the InfiniteScroll page")
    public void testInfinitePageScrollBehavior() {
        int amountOfParagraphsBefore = infiniteScrollPage.getParagraphsAmount();
        infiniteScrollPage.scrollToParagraph(infiniteScrollPage.getParagraphsAmount() - 1);
        for (int i = 0; i < 10; i++) {
            new Actions(driver).scrollByAmount(0, driver.manage().window().getSize().height).perform();
        }
        int amountOfParagraphsAfter = infiniteScrollPage.getParagraphsAmount();
        Assertions.assertNotEquals(amountOfParagraphsBefore, amountOfParagraphsAfter);
        for (int i = amountOfParagraphsBefore; i < amountOfParagraphsAfter; i++) {
            Assertions.assertEquals(infiniteScrollPage.getParagraphText(i % amountOfParagraphsBefore), infiniteScrollPage.getParagraphText(i));
        }
    }
}
