package selenium.annotations;

import org.jspecify.annotations.NonNull;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import selenium.webdriver.DriverConfig;
import selenium.webdriver.DriverFactory;
import selenium.webdriver.DriverHolder;

import java.lang.reflect.Method;
import java.util.Optional;

public class HeadlessExtension implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(@NonNull ExtensionContext context) {
        boolean headless = isHeadlessRequested(context);
        boolean proxy = isProxyRequested(context);
        boolean geo = isGeoRequested(context);
        String[] options = getAnnotationOptions(context);
        WebDriver driver = DriverFactory.getDriver(new DriverConfig(headless, proxy, geo), options);
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

    private boolean isGeoRequested(ExtensionContext context) {
        Optional<Method> testMethod = context.getTestMethod();
        if (testMethod.isPresent() && testMethod.get().isAnnotationPresent(Headless.class)) {
            return testMethod.get().getAnnotation(Headless.class).geolocation();
        }
        if (testMethod.isPresent() && testMethod.get().isAnnotationPresent(Headed.class)) {
            return testMethod.get().getAnnotation(Headed.class).geolocation();
        }
        Optional<Class<?>> testClass = context.getTestClass();
        if (testClass.isPresent() && testClass.get().isAnnotationPresent(Headless.class)) {
            return testClass.get().getAnnotation(Headless.class).geolocation();
        }
        if (testClass.isPresent() && testClass.get().isAnnotationPresent(Headed.class)) {
            return testClass.get().getAnnotation(Headed.class).geolocation();
        }
        return false;
    }

    private String[] getAnnotationOptions(ExtensionContext context) {
        Optional<Method> testMethod = context.getTestMethod();
        if (testMethod.isPresent() && testMethod.get().isAnnotationPresent(Headless.class)) {
            return testMethod.get().getAnnotation(Headless.class).options();
        }
        if (testMethod.isPresent() && testMethod.get().isAnnotationPresent(Headed.class)) {
            return testMethod.get().getAnnotation(Headed.class).options();
        }
        Optional<Class<?>> testClass = context.getTestClass();
        if (testClass.isPresent() && testClass.get().isAnnotationPresent(Headless.class)) {
            return testClass.get().getAnnotation(Headless.class).options();
        }
        if (testClass.isPresent() && testClass.get().isAnnotationPresent(Headed.class)) {
            return testClass.get().getAnnotation(Headed.class).options();
        }
        return null;
    }
}