package stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.PageFactory;
import pageobjects.LoginPage;

import static stepdefinition.Hooks.getDriver;

public class LoginStep {

    private final LoginPage loginPage;

    public LoginStep() {
        this.loginPage = new LoginPage();
        PageFactory.initElements(getDriver(), this.loginPage);
    }

    @Given("El usuario está en la página de inicio de sesión")
    public void  verifyLoginPageLoaded() {
        loginPage.isInTheLoginPage();
    }

    @When("Ingresa sus credenciales válidas y accede a la plataforma")
    public void enterValidCredentials() {
        loginPage.fillCredentials();
    }
}
