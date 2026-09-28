package selenium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.devtools.NetworkInterceptor;
import org.openqa.selenium.remote.http.HttpMethod;
import org.openqa.selenium.remote.http.HttpResponse;
import org.openqa.selenium.remote.http.Routable;
import org.openqa.selenium.remote.http.Route;

import java.util.ArrayList;
import java.util.List;

public class NetworkInterceptorService {

    private NetworkInterceptor interceptor;
    private WebDriver driver;
    private final List<Routable> routes = new ArrayList<>();

    public void mockEndpoint(WebDriver driver, HttpMethod method, String endpoint, HttpResponse httpResponse) {
        this.driver = driver;
        Route route = Route.matching(req -> req.getUri().contains(endpoint) && req.getMethod().name().equalsIgnoreCase(method.name()))
                .to(() -> req ->
                        {
                            HttpResponse modifiedResponse = new HttpResponse();
                            modifiedResponse.setStatus(httpResponse.getStatus());
                            modifiedResponse.setContent(httpResponse.getContent());
                            httpResponse.forEachHeader((name, value) -> modifiedResponse.addHeader(name, value));
                            req.forEachHeader((name, value) -> modifiedResponse.addHeader(name, value));
                            return modifiedResponse;
                        }
                );
        routes.add(route);
    }

    public void start() {
        if (interceptor != null) {
            interceptor.close();
        }
        if (!routes.isEmpty() && driver != null) {
            Route combinedRoute = Route.combine(routes);
            this.interceptor = new NetworkInterceptor(driver, combinedRoute);
        }
    }

    public void stop() {
        if (interceptor != null) {
            interceptor.close();
            interceptor = null;
        }
        routes.clear();
    }
}
