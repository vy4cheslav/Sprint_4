


import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;


@RunWith(Parameterized.class)
public class QuestionCheck {

    private static int index;
    private static String question;
    private static String answer;

    public QuestionCheck(int index,String question, String answer) {
        this.index = index;
        this.question = question;
        this.answer = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials(){
        return new Object[][] {
                { 0, "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                { 1, "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                { 2, "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30." },
                { 3, "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее." },
                { 4, "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010." },
                { 5, "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится." },
                { 6, "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои." },
                { 7, "Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области." }
        };
    }
    @Rule
    public DriverRule driverRule = new DriverRule();

    @Test
    public void accordionCheck(){
        WebDriver driver = driverRule.getDriver();
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.openQAScooter();
        mainPageObject.cookieClose();
        String actualQuestion = mainPageObject.clickQuestion(index);
        String actualAnswer = mainPageObject.answerDisplayed(index);

        Assert.assertEquals("Проверка текста вопроса", question, actualQuestion);
        Assert.assertEquals("Проверка текста ответа", answer, actualAnswer);
    }
 }
