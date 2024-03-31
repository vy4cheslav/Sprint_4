import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.startsWith;

@RunWith(Parameterized.class)
public class SuccessOrder {
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
                {MainPageObject.ORDER_BUTTON_UP,
                        "Порфирий", "Петрович", "Кутузовская 13, кв 44", "Кутузовская", "+79161615877",
                        "25.03.2024", "сутки", OrderPage.COLOR_BLACK, "Домофон 35, отличается от номера квартиры"},
                {MainPageObject.ORDER_BUTTON_BIG,
                        "Бату", "Караев", "Фестивальная 33 кв 31", "Речной Вокзал", "88005353535",
                        "27.03.2024", "двое суток", OrderPage.COLOR_GREY, "С 7 до 9"},
        };
    }

    @Rule
    public DriverRule driverRule = new DriverRule();
    @Test
    public void createNewOrderTest() {
        WebDriver driver = driverRule.getDriver();
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.openQAScooter();
        mainPageObject.cookieClose();
        mainPageObject.scrollToBigOrderButton(orderButton);
        mainPageObject.оrderButtonClick(orderButton);
        OrderPage orderPage = new OrderPage(driver);
        orderPage.setDataFirstPageOrder(firstName, secondName, adres, metroStation, phoneNumber);
        orderPage.setDataSecondPageOrder(date, rentalPeriod, color, comment);
        orderPage.clickYesButton();
        MatcherAssert.assertThat(orderPage.getTextFromPopupOrderWindow(), startsWith("Заказ оформлен"));
    }
}

