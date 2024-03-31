import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    //Поле ввода имени
    private static final By FIRST_NAME_INPUT = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private static final By SECOND_NAME_INPUT = By.xpath(".//input[@placeholder='* Фамилия']");
    // поле ввода адрес
    private static final By ADRESS_INPUT = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // поле выбора станции метро
    private static final By METRO_INPUT = By.xpath(".//input[@placeholder='* Станция метро']");
    //выбор станции из выпадающего списка
    private static final By METROSTATION_INPUT = By.className("Order_Text__2broi");
    //поле ввода телефон
    private static final By PHONE_NUMBER_INPUT = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка "Далее"
    private static final By NEXT_BUTTON = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    // поле ввода "когда привезти самокат"
    private static final By DELIVERY_DATE_INPUT = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // поле ввода срока аренды
    private static final By RENTAL_PERIOD_INPUT = By.cssSelector("div.Dropdown-root");
    // цвет самоката "чёрный жемчуг"
    public static final By COLOR_BLACK = By.id("black");
    // цвет самоката "серая безысходность"
    public static final By COLOR_GREY = By.id("grey");
    // поле ввода комментарий для курьера
    private static final By COMMENT_INPUT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private static final By PURCHASE_BUTTON = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");
    //кнопка подтвеждения заказа
    private static final By YES_BUTTON = By.xpath(".//button[contains(text(), 'Да')]");
    // Заказ оформлен
    public static final By SUCCESS_PURCHASE = By.className("Order_ModalHeader__3FDaJ");
    //форма "про аренду"
    private static final By PAGE_ORDER = By.className("App_App__15LM-");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String firstName) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
    }

    public void setSecondName(String secondName) {
        driver.findElement(SECOND_NAME_INPUT).sendKeys(secondName);
    }

    public void setAdres(String adres) {
        driver.findElement(ADRESS_INPUT).sendKeys(adres);
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(PHONE_NUMBER_INPUT).sendKeys(phoneNumber);
    }

    public void chooseMetro(String metroStation) {
        driver.findElement(METRO_INPUT).sendKeys(metroStation);
        driver.findElement(METROSTATION_INPUT).click();
    }

    public void clickNextButton() {
        driver.findElement(NEXT_BUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, (5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_ORDER));
    }

    public OrderPage setDataFirstPageOrder(String firstName, String secondName, String adres, String metroStation, String phoneNumber) {
        setName(firstName);
        setSecondName(secondName);
        setAdres(adres);
        chooseMetro(metroStation);
        setPhoneNumber(phoneNumber);
        clickNextButton();
        return this;
    }

    public void inputDate(String date) {
        driver.findElement(DELIVERY_DATE_INPUT).sendKeys(date, Keys.ENTER);
    }

    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(RENTAL_PERIOD_INPUT).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }

    public void color(By color) {
        driver.findElement(color).click();
    }

    public void comment(String comment) {
        driver.findElement(COMMENT_INPUT).sendKeys(comment);
    }

    public void clickPurchaseOrderButton() {
        driver.findElement(PURCHASE_BUTTON).click();
    }

    public OrderPage setDataSecondPageOrder(String date, String rentalPeriod, By color, String comment) {
        inputDate(date);
        chooseRentalPeriod(rentalPeriod);
        color(color);
        comment(comment);
        clickPurchaseOrderButton();
        return this;
    }

    public void clickYesButton() {
        driver.findElement(YES_BUTTON).click();
    }

    public String getTextFromPopupOrderWindow() {
        return driver.findElement(SUCCESS_PURCHASE).getText();
    }
}
