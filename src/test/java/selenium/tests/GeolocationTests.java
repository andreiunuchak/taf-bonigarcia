package selenium.tests;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.bidi.emulation.Emulation;
import org.openqa.selenium.bidi.emulation.GeolocationCoordinates;
import org.openqa.selenium.bidi.emulation.SetGeolocationOverrideParameters;
import org.openqa.selenium.bidi.module.Permission;
import org.openqa.selenium.bidi.permissions.PermissionState;
import selenium.annotations.Headless;
import selenium.pages.GeolocationPage;
import selenium.pages.HomePage;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static selenium.pages.BasePage.ORIGIN;

@Headless(geolocation = true)
public class GeolocationTests extends BaseTest {
    private GeolocationPage geolocationPage;
    private final Faker faker = new Faker();

    @BeforeEach
    public void openGeolocationPage() {
        geolocationPage = new HomePage(driver).open().clickGeolocation();
    }

    @Test
    public void testGeolocation() {
        double expectedLatitude = Double.parseDouble(faker.address().latitude());
        double expectedLongitude = Double.parseDouble(faker.address().longitude());
        new Permission(driver).setPermission(
                Map.of("name", "geolocation"),
                PermissionState.GRANTED,
                ORIGIN);
        new Emulation(driver).setGeolocationOverride(
                new SetGeolocationOverrideParameters(
                        new GeolocationCoordinates(expectedLatitude, expectedLongitude))
                        .contexts(List.of(driver.getWindowHandle())));
        String coordinatesStr = geolocationPage.clickGetGeolocation().getCoordinates();
        Pattern NUMBER = Pattern.compile("-?\\d+(?:\\.\\d+)?");
        double[] coordinates = NUMBER.matcher(coordinatesStr).results()
                .mapToDouble(r -> Double.parseDouble(r.group()))
                .toArray();
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedLatitude, coordinates[0]),
                () -> Assertions.assertEquals(expectedLongitude, coordinates[1])
        );
    }

    @Test
    public void testGeolocationWithoutPermission() {
        new Permission(driver).setPermission(
                Map.of("name", "geolocation"),
                PermissionState.DENIED,
                ORIGIN);
        String coordinatesStr = geolocationPage.clickGetGeolocation().getCoordinates();
        Assertions.assertEquals("Error accessing to current geolocation position", coordinatesStr);
    }
}
