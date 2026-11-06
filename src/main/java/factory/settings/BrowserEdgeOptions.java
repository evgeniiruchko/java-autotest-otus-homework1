package factory.settings;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class BrowserEdgeOptions implements IBrowserSettings {

    @Override
    public AbstractDriverOptions<?> settings() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        return options;
    }
}
