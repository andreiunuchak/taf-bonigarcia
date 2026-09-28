package selenium.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import net.datafaker.Faker;
import org.junit.jupiter.api.*;
import selenium.annotations.Headless;
import selenium.constants.Namespaces;
import selenium.pages.HomePage;
import selenium.pages.WebStoragePage;

@Headless
@Story(Namespaces.Stories.WEB_STORAGE)
public class WebStorageTests extends BaseTest {
    private WebStoragePage webStoragePage;
    private final Faker faker = new Faker();

    @BeforeEach
    public void openWebStoragePage() {
        webStoragePage = new HomePage(driver).open().clickWebStorage();
    }

    @Test
    @Severity(SeverityLevel.MINOR)
    @Tag(Namespaces.Severity.MINOR)
    @DisplayName("Validate Title on the WebStorage page")
    public void testWebStorageTitle() {
        String expectedTitle = "Web storage";
        String actualTitle = webStoragePage.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate LocalStorage entities add on the WebStorage page")
    public void testLocalStorage() {
        String key = faker.credentials().userId();
        String value = faker.internet().uuid();
        String actualValueUI = webStoragePage.addLocalStorage(key, value)
                .clickDisplayLocalStorage()
                .getLocalStorageUI();
        String actualValueJS = webStoragePage.getLocalStorageString(key);
        Assertions.assertAll(
                () -> Assertions.assertEquals(value, actualValueJS),
                () -> Assertions.assertEquals(String.format("{\"%s\":\"%s\"}", key, value), actualValueUI)
        );
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Tag(Namespaces.Severity.CRITICAL)
    @DisplayName("Validate SessionStorage entities add on the WebStorage page")
    public void testSessionStorage() {
        String key = faker.credentials().userId();
        String value = faker.internet().uuid();
        String actualValueUI = webStoragePage.addSessionStorage(key, value)
                .clickDisplaySessionStorage()
                .getSessionStorageUI();
        String actualValueJS = webStoragePage.getSessionStorageString(key);
        Assertions.assertAll(
                () -> Assertions.assertEquals(value, actualValueJS),
                () -> Assertions.assertTrue(actualValueUI.contains(String.format("\"%s\":\"%s\"", key, value)))
        );
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Tag(Namespaces.Severity.MEDIUM)
    @DisplayName("Validate the clear of Session and Local storages on the WebStorage page")
    public void clearStorages() {
        String actualLocalStorageUI = webStoragePage.clearLocalStorageString()
                .clickDisplayLocalStorage()
                .getLocalStorageUI();
        String actualSessionStorageUI = webStoragePage.clearSessionStorageString()
                .clickDisplaySessionStorage()
                .getSessionStorageUI();
        Assertions.assertAll(
                () -> Assertions.assertEquals("{}", actualLocalStorageUI),
                () -> Assertions.assertEquals("{}", actualSessionStorageUI)
        );
    }
}
