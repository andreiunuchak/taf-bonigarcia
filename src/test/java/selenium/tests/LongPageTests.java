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
import selenium.pages.LongPage;

import java.util.Random;

@Headless
@Story(Namespaces.Stories.LONG_PAGE)
public class LongPageTests extends BaseTest {

    private LongPage longPage;

    @BeforeEach
    public void openPage() {
        longPage = new HomePage().open().clickLongPage();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate Title on the Long page")
    public void testHomeTitle(){
        Assertions.assertEquals("This is a long page", longPage.getTitle());
    }

    @ParameterizedTest
    @MethodSource("selenium.utils.TestDataProvider#paragraphsTestData")
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.MEDIUM)
    @DisplayName("Validate paragraph text")
    public void testLongPageParagraphsTexts(int id, String expectedText) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(20, longPage.getParagraphsAmount()),
                () -> Assertions.assertEquals(expectedText, longPage.getParagraphText(id))
        );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate scroll on the LongPage")
    public void testLongPageScrollBehavior() {
        int id = new Random().nextInt(longPage.getParagraphsAmount() / 2, longPage.getParagraphsAmount() + 1);
        longPage.scrollToParagraph(id);
        Assertions.assertTrue(longPage.isParagraphInViewPort(id));
    }
}
