import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@RunWith(Parameterized.class)
public class SuccessOrder extends DriverSettings {
    private By orderButton;
    private String firstName;
    private String secondName;
    private String adres;
    private String metroStation;
    private String phoneNumber;
    private String date;
    private String rentalPeriod;
    private By color;
    private String comment;

    public SuccessOrder(By orderButton, String firstName, String secondName, String address,
                        String metroStation, String phoneNumber, String date, String rentalPeriod, By color, String comment) {
        this.orderButton = orderButton;
        this.firstName = firstName;
        this.secondName = secondName;
        this.adres = address;
        this.metroStation = metroStation;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {MainPageObject.orderButtonUp,
                        "Порфирий", "Петрович", "Кутузовская 13, кв 44", "Кутузовская", "+79161615877",
                        "25.03.2024", "сутки", OrderPage.colorBlack, "Домофон 35, отличается от номера квартиры"},
                {MainPageObject.orderButtonBig,
                        "Бату", "Караев", "Фестивальная 33 кв 31", "Речной Вокзал", "88005353535",
                        "27.03.2024", "двое суток", OrderPage.colorGrey, "С 7 до 9"},
        };
    }

    @Test
    public void createNewOrderTest() {
        MainPageObject MP = new MainPageObject(driver);
        MP.openQAScooter();
        MP.cookieClose();
        MP.ScrollToBigOrderButton(orderButton);
        MP.OrderButtonClick(orderButton);
        OrderPage OP = new OrderPage(driver);
        OP.setDataFirstPageOrder(firstName, secondName, adres, metroStation, phoneNumber);
        OP.setDataSecondPageOrder(date, rentalPeriod, color, comment);
        OP.clickYesButton();
        boolean isDisplayed = OP.isOrderWindowDisplayed();
        Assert.assertTrue(isDisplayed);
        OP.getTextFromPopupOrderWindow();
    }
}

