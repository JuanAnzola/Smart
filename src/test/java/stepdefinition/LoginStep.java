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

    @Given("El usuario está en la página de Visado")
    public void verifyLoginPageLoaded() {
        loginPage.isInTheLoginPage();
    }

    @When("El usuario se loguea de forma exitsa")
    public void enterValidCredentials() {
        loginPage.userSendCredentials();
    }
}
