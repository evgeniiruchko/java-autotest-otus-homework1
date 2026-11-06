package factory;

import exceptions.BrowserNotSupportException;
import factory.settings.BrowserChromeOptions;
import factory.settings.BrowserEdgeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.time.Duration;
import java.util.Locale;

public class WebDriverFactory {

    public WebDriver create() {
        String browserName = System.getProperty("browser.name");
        String browser = browserName == null || browserName.isBlank()
                ? "chrome"
                : browserName.toLowerCase(Locale.ROOT);

        WebDriver driver = switch (browser) {
            case "chrome" -> createChrome();
            case "edge" -> createEdge();
            default -> throw new BrowserNotSupportException(browserName);
        };

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        return driver;
    }

    private WebDriver createChrome() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = (ChromeOptions) new BrowserChromeOptions().settings();
        return new ChromeDriver(options);
    }

    private WebDriver createEdge() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = (EdgeOptions) new BrowserEdgeOptions().settings();
        return new EdgeDriver(options);
    }
}
