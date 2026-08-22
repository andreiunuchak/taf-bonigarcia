package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;

@Headless
@Story(Namespaces.Stories.HOME)
public class HomeTests extends BaseTest{
    private HomePage homePage;

    @BeforeEach
    public void openHomePage(){
        homePage = new HomePage(driver).open();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate Title on the Home page")
    public void testHomeTitle(){
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", homePage.getTitle());
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate SubTitle on the Home page")
    public void testHomeSubTitle(){
        Assertions.assertEquals("Practice site", homePage.getSubTitle());
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate Description on the Home page")
    public void testHomeDescription(){
        Assertions.assertEquals("This site contains a collection of sample web pages to be tested with Selenium WebDriver. Check out the O'Reilly book and the source code on GitHub.", homePage.getDescription());
    }

}
