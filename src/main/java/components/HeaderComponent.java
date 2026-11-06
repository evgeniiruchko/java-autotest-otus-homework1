package components;

import annotations.Component;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Component("css:nav")
public class HeaderComponent extends AbsComponent {

    @Inject
    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public void openMyWishlists() {
        getRoot().findElement(By.cssSelector("a.nav-link[href='/wishlists']")).click();
    }

    public void openUsers() {
        getRoot().findElement(By.cssSelector("a.nav-link[href='/users']")).click();
    }
}
