package components;

import annotations.Component;
import exceptions.StrategyNotSelect;
import exceptions.StrategyNotSupported;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.AbsCommon;

public abstract class AbsComponent extends AbsCommon {

    private WebElement root;

    protected AbsComponent(WebDriver driver) {
        super(driver);
    }

    protected AbsComponent(WebDriver driver, WebElement root) {
        super(driver);
        this.root = root;
    }

    public WebElement getRoot() {
        if (root == null) {
            root = waiters.waitForElementVisible(componentLocator());
        }
        return root;
    }

    private By componentLocator() {
        Component component = getClass().getAnnotation(Component.class);
        if (component == null) {
            throw new StrategyNotSelect();
        }

        String[] parts = component.value().split(":", 2);
        if (parts.length < 2) {
            throw new StrategyNotSelect();
        }

        return switch (parts[0]) {
            case "xpath" -> By.xpath(parts[1]);
            case "css" -> By.cssSelector(parts[1]);
            default -> throw new StrategyNotSupported(parts[0]);
        };
    }
}
