import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    //Поле ввода имени
    private static final By FirstNameInput = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private static final By SecondNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    // поле ввода адрес
    private static final By AdresInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // поле выбора станции метро
    private static final By MetroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    //выбор станции из выпадающего списка
    private static final By MetroStationInput = By.className("Order_Text__2broi");
    //поле ввода телефон
    private static final By PhoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка "Далее"
    private static final By NextButtonInput = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    // поле ввода "когда привезти самокат"
    private static final By DeliveryDateInput = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // поле ввода срока аренды
    private static final By RentalPeriodInput = By.cssSelector("div.Dropdown-root");
    // цвет самоката "чёрный жемчуг"
    public static final By ColorBlack = By.id("black");
    // цвет самоката "серая безысходность"
    public static final By ColorGrey = By.id("grey");
    // поле ввода комментарий для курьера
    private static final By CommentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private static final By PurchaseButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");
    //кнопка подтвеждения заказа
    private static final By YesButton = By.xpath(".//button[contains(text(), 'Да')]");
    // Заказ оформлен
    public static final By SuccessPurchase = By.className("Order_ModalHeader__3FDaJ");
    //форма "про аренду"
    private static final By PageOrder = By.className("App_App__15LM-");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String firstName) {
        driver.findElement(FirstNameInput).sendKeys(firstName);
    }

    public void setSecondName(String secondName) {
        driver.findElement(SecondNameInput).sendKeys(secondName);
    }

    public void setAdres(String adres) {
        driver.findElement(AdresInput).sendKeys(adres);
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(PhoneNumberInput).sendKeys(phoneNumber);
    }

    public void chooseMetro(String metroStation) {
        driver.findElement(MetroInput).sendKeys(metroStation);
        driver.findElement(MetroStationInput).click();
    }

    public void clickNextButton() {
        driver.findElement(NextButtonInput).click();
        WebDriverWait wait = new WebDriverWait(driver, (5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PageOrder));
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
        driver.findElement(DeliveryDateInput).sendKeys(date, Keys.ENTER);
    }

    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(RentalPeriodInput).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }

    public void color(By color) {
        driver.findElement(color).click();
    }

    public void comment(String comment) {
        driver.findElement(CommentInput).sendKeys(comment);
    }

    public void clickPurchaseOrderButton() {
        driver.findElement(PurchaseButton).click();
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
        driver.findElement(YesButton).click();
    }

    public String getTextFromPopupOrderWindow() {
        return driver.findElement(SuccessPurchase).getText();
    }
}
