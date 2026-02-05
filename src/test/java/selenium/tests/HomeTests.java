package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;

@Headless
@Story(Namespaces.Stories.HOME)
public class HomeTests extends BaseTest{
    private HomePage homePage;

    @BeforeEach
    public void openHomePage(){
        homePage = new HomePage().open();
    }

    @Test
    @DisplayName("Validate Title on the Home page")
    public void testHomeTitle(){
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", homePage.getTitle());
    }

    @Test
    @DisplayName("Validate SubTitle on the Home page")
    public void testHomeSubTitle(){
        Assertions.assertEquals("Practice site", homePage.getSubTitle());
    }

    @Test
    @DisplayName("Validate Description on the Home page")
    public void testHomeDescription(){
        Assertions.assertEquals("This site contains a collection of sample web pages to be tested with Selenium WebDriver. Check out the O'Reilly book and the source code on GitHub.", homePage.getDescription());
    }

}
