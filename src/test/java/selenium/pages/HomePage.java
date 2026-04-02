package selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends AbstractPage {
    public final String URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private final By byTitle = By.xpath("//h1");
    private final By bySubtitle = By.xpath("//h1/following-sibling::h5");
    private final By byDescription = By.xpath("//p");
    private final By byChapterThreeTitle = By.xpath("(//div[@class='card-body'])[1]/h5");
    private final By byChapterFourTitle = By.xpath("(//div[@class='card-body'])[2]/h5");
    private final By byChapterFiveTitle = By.xpath("(//div[@class='card-body'])[3]/h5");
    private final By byChapterSevenTitle = By.xpath("(//div[@class='card-body'])[4]/h5");
    private final By byChapterEightTitle = By.xpath("(//div[@class='card-body'])[5]/h5");
    private final By byChapterNineTitle = By.xpath("(//div[@class='card-body'])[6]/h5");
    private final By byWebForm = By.xpath("//a[@href='web-form.html']");
    private final By byNavigation = By.xpath("//a[@href='navigation1.html']");
    private final By byDropDownMenu = By.xpath("//a[@href='dropdown-menu.html']");
    private final By byMouseOver = By.xpath("//a[@href='mouse-over.html']");
    private final By byDragAndDrop = By.xpath("//a[@href='drag-and-drop.html']");
    private final By byDrawInCanvas = By.xpath("//a[@href='draw-in-canvas.html']");
    private final By byLoadingImages = By.xpath("//a[@href='loading-images.html']");
    private final By bySlowCalculator = By.xpath("//a[@href='slow-calculator.html']");
    private final By byLongPage = By.xpath("//a[@href='long-page.html']");
    private final By byInfiniteScroll = By.xpath("//a[@href='infinite-scroll.html']");
    private final By byShadowDom = By.xpath("//a[@href='shadow-dom.html']");
    private final By byCookies = By.xpath("//a[@href='cookies.html']");
    private final By byFrames = By.xpath("//a[@href='frames.html']");
    private final By byIFrames = By.xpath("//a[@href='iframes.html']");
    private final By byDialogBoxes = By.xpath("//a[@href='dialog-boxes.html']");
    private final By byWebStorage = By.xpath("//a[@href='web-storage.html']");
    private final By byGeolocation = By.xpath("//a[@href='geolocation.html']");
    private final By byNotifications = By.xpath("//a[@href='notifications.html']");
    private final By byGetUserMedia = By.xpath("//a[@href='get-user-media.html']");
    private final By byMultilanguage = By.xpath("//a[@href='multilanguage.html']");
    private final By byConsoleLogs = By.xpath("//a[@href='console-logs.html']");
    private final By byLoginForm = By.xpath("//a[@href='login-form.html']");
    private final By bySlowLogin = By.xpath("//a[@href='login-slow.html']");
    private final By byRandomCalculator = By.xpath("//a[@href='random-calculator.html']");
    private final By byDownloadFiles = By.xpath("//a[@href='download.html']");
    private final By byABTesting = By.xpath("//a[@href='ab-testing.html']");
    private final By byDataTypes = By.xpath("//a[@href='data-types.html']");

    @Override
    @Step("Open HomePage: " + URL)
    public HomePage open() {
        driver.get(URL);
        return this;
    }

    @Override
    @Step("Receive Title")
    public String getTitle() {
        return driver.findElement(byTitle).getText();
    }

    @Step("Receive SubTitle")
    public String getSubTitle() {
        return driver.findElement(bySubtitle).getText();
    }

    @Step("Receive Description")
    public String getDescription() {
        return driver.findElement(byDescription).getText();
    }

    @Step("Click WebForm button")
    public WebFormPage clickWebForm() {
        new Actions(driver).moveToElement(driver.findElement(byWebForm)).click().perform();
        return new WebFormPage();
    }

    @Step("Click Navigation button")
    public NavigationPage clickNavigation() {
        new Actions(driver).moveToElement(driver.findElement(byNavigation)).click().perform();
        return new NavigationPage();
    }

    @Step("Click DropDownMenu button")
    public DropdownMenuPage clickDropDownMenu() {
        new  Actions(driver).moveToElement(driver.findElement(byDropDownMenu)).click().perform();
        return new DropdownMenuPage();
    }

    @Step("Click MouseOver button")
    public MouseOverPage clickMouseOver() {
        new  Actions(driver).moveToElement(driver.findElement(byMouseOver)).click().perform();
        return new MouseOverPage();
    }

    @Step("Click DragAndDrop button")
    public DragAndDropPage clickDragAndDrop() {
        new  Actions(driver).moveToElement(driver.findElement(byDragAndDrop)).click().perform();
        return new DragAndDropPage();
    }

    @Step("Click DrawInCanvas button")
    public DrawInCanvasPage clickDrawInCanvas() {
        new  Actions(driver).moveToElement(driver.findElement(byDrawInCanvas)).click().perform();
        return new DrawInCanvasPage();
    }

    @Step("Click LoadingImages button")
    public LoadingImagesPage clickLoadingImages() {
        new  Actions(driver).moveToElement(driver.findElement(byLoadingImages)).click().perform();
        return new LoadingImagesPage();
    }

    @Step("Click SlowCalculator button")
    public SlowCalculatorPage clickSlowCalculator() {
        new  Actions(driver).moveToElement(driver.findElement(bySlowCalculator)).click().perform();
        return new SlowCalculatorPage();
    }

    @Step("Click LongPage button")
    public LongPage clickLongPage() {
        new  Actions(driver).moveToElement(driver.findElement(byLongPage)).click().perform();
        return new LongPage();
    }

    @Step("Click InfiniteScroll button")
    public InfiniteScrollPage clickInfiniteScroll() {
        new Actions(driver).moveToElement(driver.findElement(byInfiniteScroll)).click().perform();
        return new InfiniteScrollPage();
    }

    @Step("Click ShadowDom button")
    public ShadowDOMPage clickShadowDom() {
        new  Actions(driver).moveToElement(driver.findElement(byShadowDom)).click().perform();
        return new ShadowDOMPage();
    }

    @Step("Click Cookies button")
    public CookiesPage clickCookies() {
        new  Actions(driver).moveToElement(driver.findElement(byCookies)).click().perform();
        return new CookiesPage();
    }

    @Step("Click Frames button")
    public FramesPage clickFrames() {
        new  Actions(driver).moveToElement(driver.findElement(byFrames)).click().perform();
        return new FramesPage();
    }

    @Step("Click IFrames button")
    public HomePage clickIFrames() {
        new  Actions(driver).moveToElement(driver.findElement(byIFrames)).click().perform();
        return new HomePage();
    }

    @Step("Click DialogBoxes button")
    public HomePage clickDialogBoxes() {
        new   Actions(driver).moveToElement(driver.findElement(byDialogBoxes)).click().perform();
        return new HomePage();
    }

    @Step("Click WebStorage button")
    public HomePage clickWebStorage() {
        new   Actions(driver).moveToElement(driver.findElement(byWebStorage)).click().perform();
        return new HomePage();
    }

    @Step("Click Geolocation button")
    public HomePage clickGeolocation() {
        new  Actions(driver).moveToElement(driver.findElement(byGeolocation)).click().perform();
        return new HomePage();
    }

    @Step("Click Notifications button")
    public HomePage clickNotifications() {
        new  Actions(driver).moveToElement(driver.findElement(byNotifications)).click().perform();
        return new HomePage();
    }

    @Step("Click GetUserMedia button")
    public HomePage clickGetUserMedia() {
        new  Actions(driver).moveToElement(driver.findElement(byGetUserMedia)).click().perform();
        return new HomePage();
    }

    @Step("Click Multilanguage button")
    public HomePage clickMultilanguage() {
        new   Actions(driver).moveToElement(driver.findElement(byMultilanguage)).click().perform();
        return new HomePage();
    }

    @Step("Click ConsoleLogs button")
    public HomePage clickConsoleLogs() {
        new  Actions(driver).moveToElement(driver.findElement(byConsoleLogs)).click().perform();
        return new HomePage();
    }

    @Step("Click LoginForm button")
    public HomePage clickLoginForm() {
        new   Actions(driver).moveToElement(driver.findElement(byLoginForm)).click().perform();
        return new HomePage();
    }

    @Step("Click SlowLogin button")
    public HomePage clickSlowLogin() {
        new Actions(driver).moveToElement(driver.findElement(bySlowLogin)).click().perform();
        return new HomePage();
    }

    @Step("Click RandomCalculator button")
    public HomePage clickRandomCalculator() {
        new  Actions(driver).moveToElement(driver.findElement(byRandomCalculator)).click().perform();
        return new HomePage();
    }

    @Step("Click DownloadFiles button")
    public HomePage clickDownloadFiles() {
        new   Actions(driver).moveToElement(driver.findElement(byDownloadFiles)).click().perform();
        return new HomePage();
    }

    @Step("Click ABTesting button")
    public HomePage clickABTesting() {
        new   Actions(driver).moveToElement(driver.findElement(byABTesting)).click().perform();
        return new HomePage();
    }

    @Step("Click DataTypes button")
    public HomePage clickDataTypes() {
        new   Actions(driver).moveToElement(driver.findElement(byDataTypes)).click().perform();
        return new HomePage();
    }

    public String getChapterThreeTitle() {
        return driver.findElement(byChapterThreeTitle).getText();
    }

    public String getChapterFourTitle() {
        return driver.findElement(byChapterFourTitle).getText();
    }

    public String getChapterFiveTitle() {
        return driver.findElement(byChapterFiveTitle).getText();
    }

    public String getChapterSevenTitle() {
        return driver.findElement(byChapterSevenTitle).getText();
    }

    public String getChapterEightTitle() {
        return driver.findElement(byChapterEightTitle).getText();
    }

    public String getChapterNineTitle() {
        return driver.findElement(byChapterNineTitle).getText();
    }

}
