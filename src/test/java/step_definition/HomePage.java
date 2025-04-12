package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static step_definition.Hooks.getDriver;
import static utilities.PropertyUtils.getProperty;

public class HomePage {

    @FindBy(how = How.XPATH, using = "//img[@title=\"Matriculas\"]")
    private WebElement img_Programacion;

    @FindBy(how = How.XPATH, using = "//span[text() = 'ING-A1, A2, B1, B2 Y C1 PLAN 906H                 ']")
    private WebElement span_PlanEstudios;

    @FindBy(how = How.XPATH, using = "//input[@value=\"Iniciar\"]")
    private WebElement inpt_Iniciar;

    @FindBy(how = How.XPATH, using = "//iframe[@id=\"gxp0_ifrm\"]")
    private WebElement ifr_Clases;

    @FindBy(how = How.XPATH, using = "//iframe[@id=\"gxp1_ifrm\"]")
    private WebElement ifr_Agendamiento;

    @FindBy(how = How.XPATH, using = "//select[@id=\"vTPEAPROBO\"]")
    private WebElement select_EstadosClases;

    @FindBy(how = How.XPATH, using = "//option[text() = 'Pendientes por programar']")
    private WebElement opt_Pendientes;

    @FindBy(how = How.XPATH, using = "//input[@name=\"BUTTON1\"]")
    private WebElement inpt_Asignar;

    @FindBy(how = How.XPATH, using = "//span[@id=\"span_TPEAPROBO_0001\" and text()=\"Pendiente\"]")
    private WebElement span_Estado;


    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    List<String> clases = new ArrayList<>();
    LocalDate fecha = LocalDate.now();
    String diaSemana = (fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));

    public HomePage(){ PageFactory.initElements(getDriver(), this);}

    @Given("Accede a la seccion de programación y selecciona el plan de estudios")
    public void selectStudyPlan() {
        try {
            Assert.assertTrue(img_Programacion.isEnabled());
            img_Programacion.click();
            wait.until(ExpectedConditions.elementToBeClickable(span_PlanEstudios));
            Assert.assertTrue(span_PlanEstudios.isEnabled());
            span_PlanEstudios.click();
            inpt_Iniciar.click();
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }

    @When("Elige una clase disponible")
    public void selectAvailableClass() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(ifr_Clases));
            getDriver().switchTo().frame(ifr_Clases);

            Assert.assertTrue(select_EstadosClases.isEnabled());
            select_EstadosClases.click();
            opt_Pendientes.click();
            wait.until(ExpectedConditions.elementToBeClickable(span_Estado));

            for (int i = 1; i<= 10; i++){
                String xpath_Estado = String.format("//tr[%d]//td[@data-colindex=\"10\"]//span", i);
                WebElement elementoEstado = getDriver().findElement(By.xpath(xpath_Estado));
                String estadoClase = elementoEstado.getText();

                if (("Pendiente").equals(estadoClase)){
                    String xpath_NombreClase = String.format("//tr[%d]//td[@data-colindex=\"5\"]//span", i);
                    WebElement elementoNombre = getDriver().findElement(By.xpath(xpath_NombreClase));
                    String nombreClase = elementoNombre.getText();

                   if(!nombreClase.contains("SMART ZONE - ")){
                       clases.add(nombreClase);
                   }
                }
            }
            String xpath_clase = String.format("//span[text() = '%s']", clases.get(0));
            WebElement elementoClase = getDriver().findElement(By.xpath(xpath_clase));
            elementoClase.click();
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }
    @When("Inicia y confirma el agendamiento")
    public void confirmScheduling() {
        try {
            inpt_Asignar.click();
            int numeroClase = Integer.parseInt(getProperty("app.classNumber"));
            String xpath_numeroClase = String.format("//span[text()=%d]", numeroClase);
            getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.elementToBeClickable(ifr_Agendamiento));
            getDriver().switchTo().frame(ifr_Agendamiento);
            WebElement opcionDeClase = getDriver().findElement(By.xpath(xpath_numeroClase));
            opcionDeClase.click();

            System.out.println();
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }
    @Then("Debería ver un mensaje de confirmación de la clase agendada")
    public void verifyClassConfirmationMessage() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}


//diaSemana = diaSemana.substring(0, 1).toUpperCase() + diaSemana.substring(1).toLowerCase();
//System.out.println(fecha + diaSemana );
