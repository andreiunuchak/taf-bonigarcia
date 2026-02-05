package selenium.annotations;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import selenium.webdriver.DriverFactory;
import selenium.webdriver.DriverHolder;

import java.lang.reflect.Method;
import java.util.Optional;

public class HeadlessExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriver driver;
        if (isHeadlessRequested(context)) {
            driver = DriverFactory.getDriver("--headless=new");
        } else {
            driver = DriverFactory.getDriver();
        }
        DriverHolder.setDriver(driver);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        DriverHolder.removeDriver();
    }

    private boolean isHeadlessRequested(ExtensionContext context) {
        Optional<Method> testMethod = context.getTestMethod();
        if (testMethod.isPresent() && testMethod.get().isAnnotationPresent(Headless.class)) {
            return true;
        }
        if (testMethod.isPresent() && testMethod.get().isAnnotationPresent(Headed.class)) {
            return false;
        }
        Optional<Class<?>> testClass = context.getTestClass();
        return testClass.isPresent() && testClass.get().isAnnotationPresent(Headless.class);
    }
}