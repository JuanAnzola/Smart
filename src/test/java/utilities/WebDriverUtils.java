package utilities;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static stepdefinition.Hooks.getDriver;

public class WebDriverUtils {

    private static WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));

    public static void switchToIframe(WebElement iframe) {
        try {
            getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.elementToBeClickable(iframe));
            getDriver().switchTo().frame(iframe);
        } catch (TimeoutException e) {
            throw new RuntimeException("No se pudo cargar el iframe", e);
        }
    }
}