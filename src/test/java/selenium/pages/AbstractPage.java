package selenium.pages;

import org.openqa.selenium.WebDriver;
import selenium.utils.Waiters;
import selenium.webdriver.DriverHolder;

public abstract class AbstractPage {
    protected WebDriver driver;
    protected Waiters  waiters;

    public AbstractPage() {
        this.driver = DriverHolder.getDriver();
        this.waiters = new Waiters();
    }

    public abstract AbstractPage open();

    public abstract String getTitle();
}
