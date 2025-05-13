package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

import static step_definition.Hooks.getDriver;
import static utilities.PropertyUtils.getProperty;

public class HomePage {

    @FindBy(how = How.XPATH, using = "//img[@title=\"Matriculas\"]")
    private WebElement btn_Programacion;

    @FindBy(how = How.XPATH, using = "//span[text() = 'ING-A1, A2, B1, B2 Y C1 PLAN 906H                 ']")
    private WebElement opt_PlanEstudios;

    @FindBy(how = How.XPATH, using = "//input[@value=\"Iniciar\"]")
    private WebElement btn_Iniciar;

    @FindBy(how = How.XPATH, using = "//input[@title=\"Confirmar\"]")
    private WebElement btn_Confirmar;

    @FindBy(how = How.XPATH, using = "//iframe[@id=\"gxp0_ifrm\"]")
    private WebElement ifr_Clases;

    @FindBy(how = How.XPATH, using = "//iframe[@id=\"gxp1_ifrm\"]")
    private WebElement ifr_Agendamiento;

    @FindBy(how = How.XPATH, using = "//select[@id=\"vTPEAPROBO\"]")
    private WebElement select_EstadosClases;

    @FindBy(how = How.XPATH, using = "//select[@id=\"vDIA\"]")
    private WebElement select_DiaAgendar;

    @FindBy(how = How.XPATH, using = "//option[text() = 'Pendientes por programar']")
    private WebElement opt_Pendientes;

    @FindBy(how = How.XPATH, using = "//input[@name=\"BUTTON1\"]")
    private WebElement btn_Asignar;

    @FindBy(how = How.XPATH, using = "//span[@id=\"span_TPEAPROBO_0001\" and text()=\"Pendiente\"]")
    private WebElement span_EstadoClase;

    @FindBy(how = How.XPATH, using = "//div[@class=\"gx-warning-message\"]")
    private WebElement div_Mensaje;


    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    List<String> clases = new ArrayList<>();
    String xpath_DiaAgendar;
    boolean exitoso;
    LocalDate fecha;
    String diaSemana;
    int hora;

    public HomePage(){ PageFactory.initElements(getDriver(), this);}

    @Given("Accede a la seccion de programación y selecciona el plan de estudios")
    public void selectStudyPlan() {
        try {
            Assert.assertTrue(btn_Programacion.isEnabled());
            btn_Programacion.click();
            wait.until(ExpectedConditions.elementToBeClickable(opt_PlanEstudios));
            Assert.assertTrue(opt_PlanEstudios.isEnabled());
            opt_PlanEstudios.click();
            btn_Iniciar.click();
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
            wait.until(ExpectedConditions.elementToBeClickable(span_EstadoClase));

            for (int i = 1; i<= 10; i++){
                String xpath_Estado = String.format("//tr[%d]//td[@data-colindex=\"10\"]//span", i);
                WebElement elementoEstado = waitToElementoClickeable(xpath_Estado);
                String estadoClase = elementoEstado.getText();

                if (("Pendiente").equals(estadoClase)){
                    String xpath_NombreClase = String.format("//tr[%d]//td[@data-colindex=\"5\"]//span", i);
                    WebElement elementoNombre = waitToElementoClickeable(xpath_NombreClase);
                    String nombreClase = elementoNombre.getText();

                    if(!nombreClase.contains("SMART ZONE - ")){
                        clases.add(nombreClase);
                    }
                }
            }
            String xpath_clase = String.format("//span[text() = '%s']", clases.get(0));
            WebElement elementoClase = waitToElementoClickeable(xpath_clase);
            elementoClase.click();
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }
    @When("Inicia y confirma el agendamiento")
    public void confirmScheduling() {
        try {
            btn_Asignar.click();
            getDriver().switchTo().defaultContent();
            wait.until(ExpectedConditions.elementToBeClickable(ifr_Agendamiento));
            getDriver().switchTo().frame(ifr_Agendamiento);
            dayAndDate(0);
            if("Domingo".equals(diaSemana) || (16 <= hora)){
                dayAndDate(1);
                select_DiaAgendar.click();
                xpath_DiaAgendar = String.format("//option[contains(text(), '%s')]", diaSemana);
                WebElement opt_DiaAgendar = waitToElementoClickeable(xpath_DiaAgendar);
                opt_DiaAgendar.click();
            }
            int numeroClase = Integer.parseInt(getProperty("app.classNumber"));
            String xpath_numeroClase = String.format("//span[text()=%d]", numeroClase);
            WebElement opcionDeClase = waitToElementoClickeable(xpath_numeroClase);
            opcionDeClase.click();
            btn_Confirmar.click();
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }

    @Then("Verificar si hay disponibilidad y la clase fue agendada")
    public void verifyClassConfirmationMessage() {
        try {
            if(isPresent(div_Mensaje)){
                exitoso = false;
                System.out.println("No fue posible agendar");
            }else {
                exitoso = true;
                System.out.println("Si fue posible agendar");
            }
        }catch (AssertionError | Exception e){
            System.out.println("-Error: " + e);
        }
    }

    public void dayAndDate(int plusDay){
        fecha = LocalDate.now().plusDays(plusDay);
        diaSemana = (fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, new Locale("es", "ES")));
        diaSemana = diaSemana.substring(0, 1).toUpperCase() + diaSemana.substring(1).toLowerCase();
        hora = LocalTime.now().getHour();
    }

    public WebElement waitToElementoClickeable(String locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public boolean isPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }
}

