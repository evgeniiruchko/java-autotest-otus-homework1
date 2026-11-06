package modules;

import com.google.inject.AbstractModule;
import listeners.HighlightListener;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebDriver;

@RequiredArgsConstructor
public class GuicePageModule extends AbstractModule {

    private final WebDriver driver;
    private final HighlightListener highlightListener;

    @Override
    protected void configure() {
        bind(WebDriver.class).toInstance(driver);
        bind(HighlightListener.class).toInstance(highlightListener);
    }
}
