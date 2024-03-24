import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage {
    private WebDriver driver;
    //Поле ввода имени
    private static final By firstNameInput = By.xpath(".//input[@placeholder='* Имя']");
    //Поле ввода фамилии
    private static final By secondNameInput = By.xpath(".//input[@placeholder='* Фамилия']");
    // поле ввода адреса
    private static final By adresInput = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // поле выбора станции метро
    private static final By metroInput = By.xpath(".//input[@placeholder='* Станция метро']");
    //выбор станции из выпадающего списка
    private static final By metroStationInput = By.className("Order_Text__2broi");
    //поле ввода телефона
    private static final By phoneNumberInput = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка "Далее"
    private static final By nextButtonInput = By.xpath(".//div[@class='Order_NextButton__1_rCA']/button");
    // поле ввода "когда привезти самокат"
    private static final By deliveryDateInput = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // поле ввода срока аренды
    private static final By rentalPeriodInput = By.cssSelector("div.Dropdown-root");
    // цвет самоката "чёрный жемчуг"
    public static final By colorBlack = By.id("black");
    // цвет самоката "серая безысходность"
    public static final By colorGrey = By.id("grey");
    // поле ввода комментарий для курьера
    private static final By commentInput = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка "Заказать"
    private static final By purchaseButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");
    //кнопка подтвеждения заказа
    private static final By yesButton = By.xpath(".//button[contains(text(), 'Да')]");
    // Заказ оформлен
    public static final By successPurchase = By.className("Order_ModalHeader__3FDaJ");
    //форма "про аренду"
    private static final By pageOrder = By.className("App_App__15LM-");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    public void setName(String firstName){
        driver.findElement(firstNameInput).sendKeys(firstName);
    }
    public void setSecondName(String secondName){
        driver.findElement(secondNameInput).sendKeys(secondName);
    }
    public void setAdres(String adres){
        driver.findElement(adresInput).sendKeys(adres);
    }
    public void setPhoneNumber(String phoneNumber){
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }
    public void chooseMetro(String metroStation){
        driver.findElement(metroInput).sendKeys(metroStation);
        driver.findElement(metroStationInput).click();
    }
    public void clickNextButton(){
        driver.findElement(nextButtonInput).click();
        WebDriverWait wait = new WebDriverWait(driver,(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageOrder));
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
        driver.findElement(deliveryDateInput).sendKeys(date, Keys.ENTER);
    }
    public void chooseRentalPeriod(String rentalPeriod) {
        driver.findElement(rentalPeriodInput).click();
        driver.findElement(By.xpath(".//div[text()='" + rentalPeriod + "']")).click();
    }
    public void color(By color) {
        driver.findElement(color).click();
    }
    public void comment(String comment) {
        driver.findElement(commentInput).sendKeys(comment);
    }
    public void clickPurchaseOrderButton() {
        driver.findElement(purchaseButton).click();
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
        driver.findElement(yesButton).click();
    }
    public boolean isOrderWindowDisplayed() {
        new WebDriverWait(driver,3)
                .until(ExpectedConditions.visibilityOfElementLocated(successPurchase));
        return driver.findElement(successPurchase).isDisplayed();
    }
    public void getTextFromPopupOrderWindow() {
        System.out.println(driver.findElement(successPurchase).getText());
    }


}
