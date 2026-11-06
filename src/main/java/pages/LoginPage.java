package pages;

import annotations.Path;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import prepare.User;

@Path("/login")
public class LoginPage extends AbsBasePage {

    private static final By USERNAME = By.cssSelector("input[type='text']");
    private static final By PASSWORD = By.cssSelector("input[type='password']");
    private static final By SUBMIT = By.cssSelector("button[type='submit']");

    @Inject
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(User user) {
        waiters.waitForElementVisible(USERNAME).sendKeys(user.getUsername());
        driver.findElement(PASSWORD).sendKeys(user.getPassword());
        waiters.waitForElementClickable(SUBMIT).click();
        waiters.waitForUrlNotContains("/login");
    }

    public void checkOpened() {
        checkHeader("Вход в систему");
    }
}
