package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import pageobjects.HomePage;

import static stepdefinition.Hooks.getDriver;

public class HomeStep {

    private final HomePage homePage;

    public HomeStep() {
        this.homePage = new HomePage();}


    @Given("Accede a la seccion de programación y selecciona el plan de estudios")
    public void selectStudyPlan() {
        homePage.sectionClasses();
    }

    @When("Elige una clase disponible")
    public void selectAvailableClass() {
        homePage.selectTheClasess();
    }

    @When("Inicia y confirma el agendamiento para el dia de {string} en la clase numero {int}")
    public void confirmScheduling(String dia, int numeroClase) {
        homePage.startAndConfirmSchedul(dia, numeroClase);
    }

    @Then("Verificar si hay disponibilidad y la clase fue agendada")
    public void verifyClassConfirmationMessage() {
        homePage.isConfirmatedTheClass();
    }
}

