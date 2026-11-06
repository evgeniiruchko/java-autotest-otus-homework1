package extensions;

import com.google.inject.Guice;
import com.google.inject.Injector;
import factory.WebDriverFactory;
import listeners.HighlightListener;
import modules.CommonModule;
import modules.GuicePageModule;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

public class UIExtension implements BeforeEachCallback, AfterEachCallback {

    private static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(UIExtension.class);
    private static final String DRIVER = "driver";
    private static final String LISTENER = "listener";

    @Override
    public void beforeEach(ExtensionContext context) {
        WebDriver rawDriver = new WebDriverFactory().create();
        HighlightListener listener = new HighlightListener(rawDriver);
        WebDriver driver = new EventFiringDecorator<>(listener).decorate(rawDriver);

        Injector injector = Guice.createInjector(
                new CommonModule(),
                new GuicePageModule(driver, listener)
        );
        injector.injectMembers(context.getRequiredTestInstance());

        ExtensionContext.Store store = context.getStore(NAMESPACE);
        store.put(DRIVER, driver);
        store.put(LISTENER, listener);
    }

    @Override
    public void afterEach(ExtensionContext context) {
        ExtensionContext.Store store = context.getStore(NAMESPACE);
        WebDriver driver = store.get(DRIVER, WebDriver.class);
        HighlightListener listener = store.get(LISTENER, HighlightListener.class);

        try {
            if (listener != null) {
                listener.resetAll();
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
