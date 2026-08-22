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
import selenium.pages.IFramesPage;

import java.util.Random;

@Headless
@Story(Namespaces.Stories.IFRAMES)
public class IFramesTests extends BaseTest{
    private IFramesPage iframesPage;

    @BeforeEach
    public void openPage() {
        iframesPage = new HomePage(driver).open().clickIFrames();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate Title on the IFrames page")
    public void testHomeTitle(){
        Assertions.assertEquals("IFrame", iframesPage.getTitle());
    }

    @ParameterizedTest
    @MethodSource("selenium.utils.TestDataProvider#paragraphsTestData")
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.MEDIUM)
    @DisplayName("Validate paragraph text")
    public void testLongPageParagraphsTexts(int id, String expectedText) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(20, iframesPage.getParagraphsAmount()),
                () -> Assertions.assertEquals(expectedText, iframesPage.getParagraphText(id))
        );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate scroll on the IFramesPage")
    public void testLongPageScrollBehavior() {
        int id = new Random().nextInt(iframesPage.getParagraphsAmount() / 2, iframesPage.getParagraphsAmount() + 1);
        iframesPage.scrollToParagraph(id);
        Assertions.assertTrue(iframesPage.isParagraphInViewPort(id));
    }
}
