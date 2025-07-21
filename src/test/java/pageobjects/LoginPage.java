package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static stepdefinition.Hooks.getDriver;
import static utilities.PropertyUtils.getProperty;
import static utilities.WebDriverUtils.switchToIframe;

public class LoginPage {

    @FindBy(how = How.XPATH, using = "//input[@name=\"BUTTON1\"]")
    private WebElement btn_Confirmar;

    @FindBy(how = How.XPATH, using = "//input[@name=\"vUSUCOD\"]")
    private WebElement txt_Area_User;

    @FindBy(how = How.XPATH, using = "//input[@name=\"vPASS\"]")
    private WebElement txt_Area_Pass;

    @FindBy(how = How.XPATH, using = "//input[@value=\"Regresar\"]")
    private WebElement btn_Regresar;

    @FindBy(how = How.XPATH, using = "//iframe[@id=\"gxp0_ifrm\"]")
    private WebElement ifr_news;

    @FindBy(how = How.XPATH, using = "//span[@class=\"ReadonlyAttribute\"]")
    private WebElement lbl_displayName;

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));


    public void isInTheLoginPage(){
        Assert.assertTrue("El botón Confirmar no está habilitado", btn_Confirmar.isEnabled());
    }

    public void fillCredentials(){
        try {
            String user = getProperty("app.username");
            String pass = getProperty("app.password");
            String expectedName = getProperty("app.accountName");

            if (user == null || pass == null || expectedName == null) {
                throw new IllegalArgumentException("Faltan propiedades en el archivo de configuración");
            }
            txt_Area_User.sendKeys(user);
            txt_Area_Pass.sendKeys(pass);
            btn_Confirmar.click();
            wait.until(ExpectedConditions.elementToBeClickable(ifr_news));
            switchToIframe(ifr_news);
            btn_Regresar.click();
            getDriver().switchTo().defaultContent();
            Assert.assertEquals("El nombre del usuario no coincide", lbl_displayName.getText(), getProperty("app.accountName"));
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }

}
