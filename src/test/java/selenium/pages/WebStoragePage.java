package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class WebStoragePage extends BasePage{
    private final String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-storage.html";
    private final By byTitle = By.xpath("//h1[@class='display-6']");
    private final By byDisplayLocalStorage = By.id("display-local");
    private final By byDisplaySessionStorage = By.id("display-session");
    private final By byLocalStorageString = By.id("local-storage");
    private final By bySessionStorageString = By.id("session-storage");

    WebStoragePage (WebDriver driver) {
        super(driver);
    }

    @Override
    @Step("Open Web Storage:" + URL)
    public BasePage open() {
        driver.get(URL);
        waiters.waitUntilPageLoaded();
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Click DisplayLocalStorage button")
    public WebStoragePage clickDisplayLocalStorage() {
        waiters.waitUntilElementClickable(byDisplayLocalStorage).click();
        return this;
    }

    @Step("Click DisplaySessionStorage button")
    public WebStoragePage clickDisplaySessionStorage() {
        waiters.waitUntilElementClickable(byDisplaySessionStorage).click();
        return this;
    }

    @Step("Receive LocalStorage from UI")
    public String getLocalStorageUI(){
        return waiters.waitUntilElementPresent(byLocalStorageString).getText();
    }

    @Step("Receive SessionStorage from UI")
    public String getSessionStorageUI(){
        return waiters.waitUntilElementPresent(bySessionStorageString).getText();
    }

    @Step("Add to LocalStorage {key}, {value}")
    public WebStoragePage addLocalStorage(String key, String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem(arguments[0], arguments[1]);", key, value);
        return this;
    }

    @Step("Add to LocalStorage {key}, {value}")
    public WebStoragePage addSessionStorage(String key, String value){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.sessionStorage.setItem(arguments[0], arguments[1]);", key, value);
        return this;
    }

    @Step("Receive data from LocalStorage by key={key}")
    public String getLocalStorageString(String key){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(String.format("return window.localStorage.getItem('%s');", key));
    }

    @Step("Receive data from SessionStorage by key={key}")
    public String getSessionStorageString(String key){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript(String.format("return window.sessionStorage.getItem('%s');", key));
    }

    @Step("Clear LocalStorage")
    public WebStoragePage clearLocalStorageString(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.clear();");
        return this;
    }

    @Step("Clear LocalStorage")
    public WebStoragePage clearSessionStorageString(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.sessionStorage.clear();");
        return this;
    }
}
