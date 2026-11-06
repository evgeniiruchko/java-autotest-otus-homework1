package pages;

import annotations.Path;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbsBasePage extends AbsCommon {

    private final String baseUrl = System.getProperty("base.url");

    protected AbsBasePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(baseUrl + getPath());
    }

    public void checkHeader(String expectedHeader) {
        WebElement header = waiters.waitForElementVisible(By.cssSelector("h1, h2"));
        Assertions.assertThat(header.getText())
                .as("Заголовок страницы")
                .isEqualTo(expectedHeader);
    }

    public void checkUrlContains(String fragment) {
        Assertions.assertThat(waiters.waitForUrlContains(fragment))
                .as("URL должен содержать %s", fragment)
                .isTrue();
        Assertions.assertThat(driver.getCurrentUrl())
                .as("Текущий URL")
                .contains(fragment);
    }

    protected void waitForLoading() {
        // У индикатора загрузки нет своего класса, а CSS не умеет искать по тексту,
        // поэтому здесь XPath.
        waiters.waitForInvisibility(By.xpath("//*[normalize-space()='Загрузка...']"));
    }

    private String getPath() {
        Path path = getClass().getAnnotation(Path.class);
        return path == null ? "" : path.value();
    }
}
