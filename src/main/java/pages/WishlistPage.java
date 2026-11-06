package pages;

import com.google.inject.Inject;
import components.GiftCardComponent;
import models.Gift;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class WishlistPage extends AbsBasePage {

    @Inject
    public WishlistPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waitForLoading();
        waiters.waitForElementsCountAtLeast(By.cssSelector(".card"), 1);
    }

    public List<Gift> getGifts() {
        return driver.findElements(By.cssSelector(".card")).stream()
                .map(element -> new GiftCardComponent(driver, element))
                .map(giftCardComponent -> giftCardComponent.toGift())
                .toList();
    }
}
