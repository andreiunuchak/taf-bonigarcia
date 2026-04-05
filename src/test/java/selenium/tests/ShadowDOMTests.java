package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.ShadowDOMPage;

@Headless
@Story(Namespaces.Stories.SHADOW_DOM)
public class ShadowDOMTests extends BaseTest {
    private ShadowDOMPage shadowDOMPage;

    @BeforeEach
    public void openPage() {
        shadowDOMPage = new HomePage().open().clickShadowDom();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate the title on the ShadowDOM page")
    public void testShadowDOMTitle() {
        Assertions.assertEquals("Shadow DOM", shadowDOMPage.getTitle());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate the text on the ShadowDOM page")
    public void testShadowDOMText() {
        Assertions.assertEquals("Hello Shadow DOM", shadowDOMPage.getText());
    }

}
