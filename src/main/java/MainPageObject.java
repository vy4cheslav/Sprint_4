import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageObject {
    private  WebDriver driver;
    // верхняя кнопка "Заказать"
    public static final By orderButtonUp = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[contains(text(),'Заказать')]");
    // большая кнопка "Заказать"
    public static final By orderButtonBig = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[contains(text(),'Заказать')]");

    // Закрытия куков
    private static final By cookieClose = By.id("rcc-confirm-button");
    // Селектор для скрола до вопросов
    private static final By questionsBar = By.cssSelector(".Home_FAQ__3uVm4");

    public MainPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public MainPageObject openQAScooter() {
        driver.get("https://qa-scooter.praktikum-services.ru/");

        return this;
    }
    public String clickQuestion(int index) {
        By question = By.id(String.format("accordion__heading-%s", index));
        WebElement questionElement = driver.findElement(question);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(question));
        questionElement.click();
        return  questionElement.getText();
    }
    public String answerDisplayed(int index) {
        WebElement answerElement = driver.findElement(By.id(String.format("accordion__panel-%s", index)));
        return answerElement.getText();
    }

    public MainPageObject cookieClose() {
        driver.findElement(cookieClose).click();

        return this;
    }
    public MainPageObject ScrollToBigOrderButton(By orderButton) {
        WebElement element = driver.findElement(orderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }
    public MainPageObject OrderButtonClick (By orderButton) {
        driver.findElement(orderButton).click();
        return this;
    }

}
