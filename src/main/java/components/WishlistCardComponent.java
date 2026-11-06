package components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishlistCardComponent extends AbsComponent {

    public WishlistCardComponent(WebDriver driver, WebElement root) {
        super(driver, root);
    }

    public String getTitle() {
        return getRoot().findElement(By.cssSelector(".card-title")).getText().trim();
    }

    public void open() {
        getRoot().findElement(By.cssSelector("a[href^='/wishlists/']")).click();
    }
}
