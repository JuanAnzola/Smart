package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;


public class HomePage {

    @FindBy(how = How.XPATH, using = "//input[@name=\"BUTTON1\"]")
    private WebElement btn_Confirmar;

    @FindBy(how = How.XPATH, using = "//input[@name=\"vUSUCOD\"]")
    private WebElement txt_Area_User;

    @FindBy(how = How.XPATH, using = "//input[@name=\"vUSUCOD\"]")
    private WebElement txt_Area_Pass;


    ChromeDriver driver = Hooks.getDriver();
    Properties properties = new Properties();

    public HomePage(){
        PageFactory.initElements(driver, this);
    }

    @Given("El usuario está en la página de inicio de sesión")
    public void login_in_to_the_page_and_enter_in_the_personal_account() {
        try {
            Assert.assertTrue(btn_Confirmar.isEnabled());
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }

    @When("Ingresa sus credenciales válidas y accede a la plataforma")
    public void ingresa_sus_credenciales_válidas_y_accede_a_la_plataforma() {
        try {
            txt_Area_User.click();
            txt_Area_User.sendKeys(properties.getProperty("app.username"));
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }
    @Then("Debería ser redirigido al home")
    public void debería_ser_redirigido_al_home() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Given("El usuario está en la sección de programación")
    public void el_usuario_está_en_la_sección_de_programación() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("Selecciona el plan de estudios")
    public void selecciona_el_plan_de_estudios() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("Elige una clase disponible")
    public void elige_una_clase_disponible() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("Inicia y confirma el agendamiento")
    public void inicia_y_confirma_el_agendamiento() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("Debería ver un mensaje de confirmación de la clase agendada")
    public void debería_ver_un_mensaje_de_confirmación_de_la_clase_agendada() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
