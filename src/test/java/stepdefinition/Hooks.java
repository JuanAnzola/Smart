package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.PropertyUtils;

public class Hooks {

    private static WebDriver driver;

    @Before
    public void setUp() {
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Ruta del driver ");

            ChromeOptions options = new ChromeOptions();

            // Perfil dedicado
            options.addArguments("user-data-dir=C:\\Ruta del perfil");
            options.addArguments("profile-directory=Profile 4");

            // (opcional) evita que intente conectarse a DevTools
            options.addArguments("--remote-debugging-port=9222");

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.get(PropertyUtils.getProperty("app.url"));
        } catch (Exception e) {
            System.out.println("Driver error: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Final error: " + e.getMessage());
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
