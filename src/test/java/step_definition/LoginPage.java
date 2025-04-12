package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static step_definition.Hooks.getDriver;
import static utilities.PropertyUtils.getProperty;

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

    public LoginPage(){
        PageFactory.initElements(getDriver(), this);
    }

    @Given("El usuario está en la página de inicio de sesión")
    public void  verifyLoginPageLoaded() {
        try {
            Assert.assertTrue(btn_Confirmar.isEnabled());
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }

    @When("Ingresa sus credenciales válidas y accede a la plataforma")
    public void enterValidCredentials() {
        try {
            getDriver();
            txt_Area_User.sendKeys(getProperty("app.username"));
            txt_Area_Pass.sendKeys(getProperty("app.password"));
            btn_Confirmar.click();
            wait.until(ExpectedConditions.elementToBeClickable(ifr_news));
            getDriver().switchTo().frame(ifr_news);
            btn_Regresar.click();
            getDriver().switchTo().defaultContent();
            Assert.assertEquals(lbl_displayName.getText(), getProperty("app.accountName"));
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }
}
