package selenium.tests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.CookiesPage;
import selenium.pages.HomePage;

import java.util.Set;
import java.util.stream.Collectors;

@Headless
@Story(Namespaces.Stories.COOKIES)
public class CookiesTests extends BaseTest {
    private CookiesPage cookiesPage;

    @BeforeEach
    public void openPage() {
        cookiesPage = new HomePage().open().clickCookies();
    }

    @Test
    @DisplayName("Validate the title on the Cookies page")
    public void testNavigationTitle() {
        Assertions.assertEquals("Cookies", cookiesPage.getTitle());
    }

    @Test
    @DisplayName("Validate default Cookies")
    public void testDefaultCookies() {
        Set<Cookie> uiCookiesList = cookiesPage.clickDisplayCookies()
                .getUICookiesList();
        Set<Cookie> appCookiesList = cookiesPage.getApplicationCookiesList();
        Set<Cookie> shortenAppCookiesList = appCookiesList.stream().map(cookie -> new Cookie(cookie.getName(), cookie.getValue())).collect(Collectors.toSet());
        Assertions.assertEquals(uiCookiesList, shortenAppCookiesList);
    }

    @Test
    @DisplayName("Validate adding Cookies")
    public void testAddCookies() {
        Cookie addCookie = new Cookie("newCookieName", "newCookieValue");
        Set<Cookie> uiCookiesList = cookiesPage
                .addApplicationCookie(addCookie)
                .clickDisplayCookies()
                .getUICookiesList();
        Set<Cookie> appCookiesList = cookiesPage.getApplicationCookiesList();
        Assertions.assertAll(
                () -> Assertions.assertTrue(appCookiesList.contains(addCookie)),
                () -> Assertions.assertTrue(uiCookiesList.contains(addCookie))
        );
    }

    @Test
    @DisplayName("Validate deleting Cookies")
    public void testDeleteCookies() {
        Cookie deleteCookie = cookiesPage.clickDisplayCookies().getUICookiesList().stream().toList().getFirst();
        Set<Cookie> uiCookiesList = cookiesPage
                .deleteApplicationCookie(deleteCookie)
                .clickDisplayCookies()
                .getUICookiesList();
        Set<Cookie> appCookiesList = cookiesPage.getApplicationCookiesList();
        Assertions.assertAll(
                () -> Assertions.assertFalse(appCookiesList.contains(deleteCookie)),
                () -> Assertions.assertFalse(uiCookiesList.contains(deleteCookie))
        );
    }
}
