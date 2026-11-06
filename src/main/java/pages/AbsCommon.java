package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import waiters.Waiters;

public class AbsCommon {

    protected final WebDriver driver;
    protected final Actions actions;
    protected final Waiters waiters;

    public AbsCommon(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.waiters = new Waiters(driver);
        PageFactory.initElements(driver, this);
    }
}
