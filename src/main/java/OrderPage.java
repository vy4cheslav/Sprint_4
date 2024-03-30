import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    //Поле ввода имени
    private static final By FIRSTNAMEINPUT = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private static final By SECONDNAMEINPUT = By.xpath(".//input[@placeholder='* Фамилия']");
    // поле ввода адрес
    private static final By ADRESSINPUT = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // поле выбора станции метро
    private static final By METROINPUT = By.xpath(".//input[@placeholder='* Станция метро']");
    //выбор станции из выпадающего списка
    private static final By METROSTATIONINPUT = By.className("Order_Text__2broi");
    //поле ввода телефон
    private static final By PHONENUMBERINPUT = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка "Далее"
    private static final By NEXTBUTTON = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    // поле ввода "когда привезти самокат"
    private static final By DELIVERYDATEINPUT = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // поле ввода срока аренды
    private static final By RENTALPERIODINPUT = By.cssSelector("div.Dropdown-root");
    // цвет самоката "чёрный жемчуг"
    public static final By COLORBLACK = By.id("black");
    // цвет самоката "серая безысходность"
    public static final By COLORGREY = By.id("grey");
    // поле ввода комментарий для курьера
    private static final By COMMENTINPUT = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private static final By PURCHASEBUTTON = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");
    //кнопка подтвеждения заказа
    private static final By YESBUTTON = By.xpath(".//button[contains(text(), 'Да')]");
    // Заказ оформлен
    public static final By SUCCESSPURCHASE = By.className("Order_ModalHeader__3FDaJ");
    //форма "про аренду"
    private static final By PAGEORDER = By.className("App_App__15LM-");


    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String firstName) {
        driver.findElement(FIRSTNAMEINPUT).sendKeys(firstName);
    }

    public void setSecondName(String secondName) {
        driver.findElement(SECONDNAMEINPUT).sendKeys(secondName);
    }

    public void setAdres(String adres) {
        driver.findElement(ADRESSINPUT).sendKeys(adres);
    }

    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(PHONENUMBERINPUT).sendKeys(phoneNumber);
    }

    public void chooseMetro(String metroStation) {
        driver.findElement(METROINPUT).sendKeys(metroStation);
        driver.findElement(METROSTATIONINPUT).click();
    }

    public void clickNextButton() {
        driver.findElement(NEXTBUTTON).click();
        WebDriverWait wait = new WebDriverWait(driver, (5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGEORDER));
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
        driver.findElement(DELIVERYDATEINPUT).sendKeys(date, Keys.ENTER);
    }

    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(RENTALPERIODINPUT).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }

    public void color(By color) {
        driver.findElement(color).click();
    }

    public void comment(String comment) {
        driver.findElement(COMMENTINPUT).sendKeys(comment);
    }

    public void clickPurchaseOrderButton() {
        driver.findElement(PURCHASEBUTTON).click();
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
        driver.findElement(YESBUTTON).click();
    }

    public String getTextFromPopupOrderWindow() {
        return driver.findElement(SUCCESSPURCHASE).getText();
    }
}
