package components;

import models.Gift;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;

public class GiftCardComponent extends AbsComponent {

    public GiftCardComponent(WebDriver driver, WebElement root) {
        super(driver, root);
    }

    public String getName() {
        return getRoot().findElement(By.cssSelector(".card-title")).getText().trim();
    }

    public String getDescription() {
        return getTextBlocks().get(0).getText().trim();
    }

    public BigDecimal getPrice() {
        String price = getTextBlocks().stream()
                .map(webElement -> webElement.getText())
                .filter(text -> text.contains("Цена:"))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("В карточке подарка нет цены"));

        String value = price.substring(price.indexOf("Цена:") + "Цена:".length());
        String normalized = value
                .replace('\u00A0', ' ')
                .replaceAll("[^0-9,.]", "")
                .replace(',', '.');

        return new BigDecimal(normalized);
    }

    public Gift toGift() {
        return new Gift(getName(), getDescription(), getPrice());
    }

    private List<WebElement> getTextBlocks() {
        return getRoot().findElements(By.cssSelector(".card-text"));
    }
}
