package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CookiesPage extends AbstractPage {
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/cookies.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byDisplayCookies = By.id("refresh-cookies");
    private final By byCookiesList = By.id("cookies-list");

    @Override
    @Step("Open DragAndDropPage:" + URL)
    public CookiesPage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Click DisplayCookies button")
    public CookiesPage clickDisplayCookies() {
        driver.findElement(byDisplayCookies).click();
        return this;
    }

    @Step("Receive cookies from UI")
    public Set<Cookie> getUICookiesList() {
        return Arrays.stream(driver.findElement(byCookiesList).getText().split("\n"))
                .map(x -> new Cookie(x.split("=")[0], x.split("=")[1]))
                .collect(Collectors.toSet());
    }

    @Step("Receive cookies from Application")
    public Set<Cookie> getApplicationCookiesList() {
        return driver.manage().getCookies();
    }

    @Step("Add cookie '{cookie}' to Application")
    public CookiesPage addApplicationCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
        return this;
    }

    @Step("Delete cookie '{cookie}' from Application")
    public CookiesPage deleteApplicationCookie(Cookie cookie) {
        driver.manage().deleteCookie(cookie);
        return this;
    }

}
