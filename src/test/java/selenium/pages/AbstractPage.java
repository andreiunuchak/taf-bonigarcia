package selenium.pages;

import org.openqa.selenium.WebDriver;
import selenium.webdriver.DriverHolder;

public abstract class AbstractPage {
    protected WebDriver driver;

    public AbstractPage() {
        this.driver = DriverHolder.getDriver();
    }

    public abstract AbstractPage open();

    public abstract String getTitle();
}
