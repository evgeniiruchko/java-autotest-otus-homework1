package waiters;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RequiredArgsConstructor
public class Waiters {

    private static final Duration TIMEOUT = Duration.ofSeconds(20);

    private final WebDriver driver;

    public <T> T waitFor(ExpectedCondition<T> condition) {
        return new WebDriverWait(driver, TIMEOUT).until(condition);
    }

    public boolean waitForCondition(ExpectedCondition<?> condition) {
        try {
            waitFor(condition);
            return true;
        } catch (TimeoutException ignored) {
            return false;
        }
    }

    public WebElement waitForElementVisible(By locator) {
        return waitFor(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return waitFor(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean waitForInvisibility(By locator) {
        return waitForCondition(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitForElementsCountAtLeast(By locator, int count) {
        return waitForCondition(driver -> driver.findElements(locator).size() >= count);
    }

    public boolean waitForUrlContains(String fragment) {
        return waitForCondition(ExpectedConditions.urlContains(fragment));
    }

    public boolean waitForUrlNotContains(String fragment) {
        return waitForCondition(driver -> {
            String currentUrl = driver.getCurrentUrl();
            return currentUrl != null && !currentUrl.contains(fragment);
        });
    }
}
