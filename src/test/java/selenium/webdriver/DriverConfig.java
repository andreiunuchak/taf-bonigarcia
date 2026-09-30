package selenium.webdriver;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public final class DriverConfig {
    private boolean headless;
    private boolean proxy;
    private boolean geolocation;
}
