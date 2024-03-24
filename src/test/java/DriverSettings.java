import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Базовый тестовый класс, в котором настраивается драйвер.
public class DriverSettings {
    protected WebDriver driver;

   //  Инициализируем драйвер и указываем, какой использовать: ChromeDriver() или FirefoxDriver()
    @Before
    public void startUp() {
       // WebDriverManager.firefoxdriver().setup();
        driver = new ChromeDriver();
       // driver = new FirefoxDriver(); // FireFox browser
    }
    @After
    public void tearDown() {
        // Закрываем браузер
        driver.quit();
    }

}
