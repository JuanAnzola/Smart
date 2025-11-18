package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static stepdefinition.Hooks.getDriver;

public class WebDriverUtils {

    @FindBy(how = How.XPATH, using = "//div[@class=\"spin-loading-container\"]")
    private static WebElement span_SpinerLoadC;

    private static int x = 0;

    public static WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));

    public static void switchToIframe(WebElement iframe) {
        try {
            getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.elementToBeClickable(iframe));
            getDriver().switchTo().frame(iframe);
        } catch (TimeoutException e) {
            throw new RuntimeException("No se pudo cargar el iframe", e);
        }
    }

    public static void scrollToElement(WebElement element) throws InterruptedException {
        try {
            if(element.isDisplayed()) {
                JavascriptExecutor js = (JavascriptExecutor) getDriver();
                js.executeScript("arguments[0].scrollIntoView(true);", element);
            }
        }catch(Exception e){
            System.out.println("Error scrolling element in: " + element + ".");
        }
    }

}



