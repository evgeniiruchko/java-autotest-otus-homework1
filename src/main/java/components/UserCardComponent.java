package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserCardComponent extends AbsComponent {

    public UserCardComponent(WebDriver driver, WebElement root) {
        super(driver, root);
    }

    public String getUsername() {
        return getRoot().findElement(By.cssSelector(".card-title")).getText().trim();
    }

    public WebElement getWishlistsButton() {
        return getRoot().findElement(By.cssSelector("a.btn[href$='/wishlists']"));
    }

    public void openWishlists() {
        actions.moveToElement(getRoot()).perform();
        actions.moveToElement(getWishlistsButton()).click().perform();
    }
}
