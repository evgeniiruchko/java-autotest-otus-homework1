package pages;

import annotations.Path;
import com.google.inject.Inject;
import components.WishlistCardComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Path("/wishlists")
public class WishlistsPage extends AbsBasePage {

    @Inject
    public WishlistsPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waiters.waitForElementsCountAtLeast(By.cssSelector(".card"), 1);
    }

    public List<WishlistCardComponent> getWishlists() {
        return driver.findElements(By.cssSelector(".card")).stream()
                .map(element -> new WishlistCardComponent(driver, element))
                .toList();
    }

    public WishlistCardComponent findWishlist(String title) {
        return getWishlists().stream()
                .filter(card -> card.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Список не найден: " + title));
    }

    public void openWishlist(String title) {
        findWishlist(title).open();
    }
}
