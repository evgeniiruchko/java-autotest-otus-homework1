package pages;

import annotations.Path;
import com.google.inject.Inject;
import components.UserCardComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

@Path("/users")
public class UsersPage extends AbsBasePage {

    @Inject
    public UsersPage(WebDriver driver) {
        super(driver);
    }

    public void waitUntilLoaded() {
        waitForLoading();
        waiters.waitForElementsCountAtLeast(By.cssSelector(".card"), 1);
    }

    public int getUsersCount() {
        return getUsers().size();
    }

    public List<UserCardComponent> getUsers() {
        return driver.findElements(By.cssSelector(".card")).stream()
                .map(element -> new UserCardComponent(driver, element))
                .toList();
    }

    public UserCardComponent getSecondUser() {
        waiters.waitForElementsCountAtLeast(By.cssSelector(".card"), 2);
        return getUsers().get(1);
    }
}
