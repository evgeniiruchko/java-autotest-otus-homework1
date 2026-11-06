package pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;

public class UserWishlistsPage extends AbsBasePage {

    @Inject
    public UserWishlistsPage(WebDriver driver) {
        super(driver);
    }

    public void checkOpened() {
        checkUrlContains("/wishlists");
        checkHeader("Списки желаний пользователя");
    }
}
