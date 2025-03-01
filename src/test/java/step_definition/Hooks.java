package step_definition;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import java.io.IOException;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.PropertyUtils;


public class Hooks {
    //Properties properties = new Properties();
    private static final ChromeDriver driver = new ChromeDriver();


    @Before
    public void setUp() throws IOException {
        //properties.load(new FileReader("src\\test\\resources\\configuration\\application.properties"));
        try {
            System.setProperty("webdriver.chrome.driver", "C:\\Driver/chromedriver.exe");
            driver.get(PropertyUtils.getProperty("app.url"));
            driver.manage().window().maximize();

        } catch (Exception e) {
            System.out.println("Driver error:" + e.getMessage());
        }
    }


    @After
    public void tearDown() {
        try {
            driver.close();
        } catch (Exception e) {
            System.out.println("Final error:" + e.getMessage());
        }
    }

    public static ChromeDriver getDriver() {
        return driver;
    }
}