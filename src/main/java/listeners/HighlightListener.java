package listeners;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class HighlightListener implements WebDriverListener {

    private static final String OUTLINE = "3px solid #dc3545";
    private static final String HIGHLIGHTED = "data-highlighted";
    private static final String DELAY_PROPERTY = "highlight.delay.ms";
    private static final long DEFAULT_DELAY_MS = 1000L;
    private static final String RESET_SCRIPT =
            "document.querySelectorAll('[" + HIGHLIGHTED + "]').forEach(function (element) {"
                    + " element.removeAttribute('" + HIGHLIGHTED + "');"
                    + " element.style.outline = '';"
                    + " element.style.outlineOffset = '';"
                    + "});";

    private final JavascriptExecutor javascriptExecutor;
    private final long delayMs;

    public HighlightListener(WebDriver driver) {
        this.javascriptExecutor = (JavascriptExecutor) driver;
        this.delayMs = Long.getLong(DELAY_PROPERTY, DEFAULT_DELAY_MS);
    }

    @Override
    public void beforeClick(WebElement element) {
        Object card = javascriptExecutor.executeScript("return arguments[0].closest('.card');", element);
        if (card instanceof WebElement webElement) {
            highlight(webElement);
        }
    }

    public void highlight(WebElement element) {
        javascriptExecutor.executeScript(
                "arguments[0].setAttribute(arguments[1], 'true');"
                        + " arguments[0].style.outline = arguments[2];"
                        + " arguments[0].style.outlineOffset = '2px';",
                element,
                HIGHLIGHTED,
                OUTLINE
        );
        pauseForVisibility();
    }

    public boolean isHighlighted(WebElement element) {
        Object outlineStyle = javascriptExecutor.executeScript(
                "return getComputedStyle(arguments[0]).outlineStyle;",
                element
        );
        return "solid".equals(outlineStyle);
    }

    public void resetAll() {
        javascriptExecutor.executeScript(RESET_SCRIPT);
    }

    private void pauseForVisibility() {
        if (delayMs <= 0) {
            return;
        }

        try {
            Thread.sleep(delayMs);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        }
    }
}
