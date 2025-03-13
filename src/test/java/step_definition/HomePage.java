package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import static step_definition.Hooks.getDriver;

public class HomePage {

    @FindBy(how = How.XPATH, using = "//img[@title=\"Matriculas\"]")
    private WebElement btn_Programacion;

    public HomePage(){ PageFactory.initElements(getDriver(), this);}

    @Given("Accede a la seccion de programación y selecciona el plan de estudios")
    public void selectStudyPlan() {
        try {
            Assert.assertTrue(btn_Programacion.isEnabled());
            btn_Programacion.click();

            System.out.println("");
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }

    @When("Elige una clase disponible")
    public void selectAvailableClass() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("Inicia y confirma el agendamiento")
    public void confirmScheduling() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("Debería ver un mensaje de confirmación de la clase agendada")
    public void verifyClassConfirmationMessage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
