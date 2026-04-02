package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        iframesPage = new HomePage().open().clickIFrames();
    }

    @Test
    @DisplayName("Validate Title on the IFrames page")
    public void testHomeTitle(){
        Assertions.assertEquals("IFrame", iframesPage.getTitle());
    }

    @ParameterizedTest
    @MethodSource("selenium.utils.TestDataProvider#paragraphsTestData")
    @DisplayName("Validate paragraph text")
    public void testLongPageParagraphsTexts(int id, String expectedText) {
        Assertions.assertAll(
                () -> Assertions.assertEquals(20, iframesPage.getParagraphsAmount()),
                () -> Assertions.assertEquals(expectedText, iframesPage.getParagraphText(id))
        );
    }

    @Test
    @DisplayName("Validate scroll on the IFramesPage")
    public void testLongPageScrollBehavior() {
        int id = new Random().nextInt(iframesPage.getParagraphsAmount() / 2, iframesPage.getParagraphsAmount() + 1);
        iframesPage.scrollToParagraph(id);
        Assertions.assertTrue(iframesPage.isParagraphInViewPort(id));
    }
}
