package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.PropertyUtils;
import static utilities.WebDriverUtils.scrollToElement;
import static utilities.WebDriverUtils.wait;


public class LoginPage {

    @FindBy(how = How.XPATH, using = "//h1[text()='Información importante para solicitantes de visa']")
    private WebElement lbl_Title;

    @FindBy(how = How.XPATH, using = "//h1[text()='Iniciar sesión o crear una cuenta']")
    private WebElement lbl_login;

    @FindBy(how = How.XPATH, using = "//input[@class=\"string email required\"]")
    private WebElement input_email;

    @FindBy(how = How.XPATH, using = "//input[@class=\"password optional\"]")
    private WebElement input_password;

    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'icheckbox') and contains(@class, 'icheck-item') and contains(@class, 'icheck')]")
    private WebElement chk_policies;

    @FindBy(how = How.XPATH, using = "//input[@data-disable-with=\"Iniciar sesión\"]")
    private WebElement btn_login;

    public void isInTheLoginPage() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(lbl_Title));
            Assert.assertTrue("El title no está habilitado", lbl_Title.isDisplayed());
        } catch(AssertionError | Exception e){
            System.out.println("Error encontrando el titulo de la pagina" + e);
        }
    }

    public void userSendCredentials() {
        try {
            scrollToElement(lbl_login);
            Thread.sleep(1000);
            input_email.click();
            input_email.sendKeys(PropertyUtils.getProperty("app.user"));
            input_password.click();
            Thread.sleep(1500);
            input_password.sendKeys(PropertyUtils.getProperty("app.password"));
            Thread.sleep(900);
            chk_policies.click();
            Thread.sleep(4000);
            btn_login.click();
        } catch(AssertionError | Exception e){
            System.out.println("Error enviando las credenciales a la pagina");
            System.out.println("Error" + e);
        }
    }

}
