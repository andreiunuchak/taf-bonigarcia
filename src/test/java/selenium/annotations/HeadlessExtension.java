package selenium.annotations;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.extension.*;
import org.openqa.selenium.WebDriver;
import selenium.webdriver.DriverFactory;
import selenium.webdriver.DriverHolder;

import java.lang.reflect.Method;
import java.util.Optional;

public class HeadlessExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(@NonNull ExtensionContext context) {
        WebDriver driver;
        boolean toProxy = isProxyRequested(context);
        if (isHeadlessRequested(context)) {
            driver = DriverFactory.getDriver(toProxy, "--headless=new");
        } else {
            driver = DriverFactory.getDriver(toProxy);
        }
        DriverHolder.setDriver(driver);
    }

    @Override
    public void afterEach(@NonNull ExtensionContext context) {
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

    private boolean isProxyRequested(ExtensionContext context) {
        Optional<Method> testMethod = context.getTestMethod();
        if (testMethod.isPresent() && testMethod.get().isAnnotationPresent(Headless.class)) {
            return testMethod.get().getAnnotation(Headless.class).proxy();
        }
        if (testMethod.isPresent() && testMethod.get().isAnnotationPresent(Headed.class)) {
            return testMethod.get().getAnnotation(Headed.class).proxy();
        }
        Optional<Class<?>> testClass = context.getTestClass();
        if (testClass.isPresent() && testClass.get().isAnnotationPresent(Headless.class)) {
            return testClass.get().getAnnotation(Headless.class).proxy();
        }
        if (testClass.isPresent() && testClass.get().isAnnotationPresent(Headed.class)) {
            return testClass.get().getAnnotation(Headed.class).proxy();
        }
        return false;
    }
}