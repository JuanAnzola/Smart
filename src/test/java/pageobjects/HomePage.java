package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import static stepdefinition.Hooks.getDriver;
import static utilities.WebDriverUtils.scrollToElement;

public class HomePage {

    @FindBy(how = How.XPATH, using = "//a[text()='Continuar']")
    private WebElement btn_Continuar;

    @FindBy(how = How.XPATH, using = "//h3[text()='Tomar medidas para este grupo de solicitantes:']")
    private WebElement lbl_Title;

    @FindBy(how = How.XPATH, using = "//h5[contains(normalize-space(.), 'Reprogramar cita')]")
    private WebElement lbl_ReprogramarCita;

    @FindBy(how = How.XPATH, using = "//a[normalize-space(text())='Reprogramar cita']")
    private WebElement btn_Reprogramar;

    @FindBy(how = How.XPATH, using = "//div[text()='Reprogramar cita']")
    private WebElement lbl_Reprogramar_Section;

    @FindBy(how = How.XPATH, using = "//input[@value=\"Continuar\"]")
    private WebElement btn_Continuar_Section;

    @FindBy(how = How.XPATH, using = "//legend[text()='Cita en la Sección Consular']")
    private WebElement lbl_cita;

    @FindBy(how = How.XPATH, using = "//select[@name=\"appointments[consulate_appointment][facility_id]\"]")
    private WebElement sel_ciudad;

    @FindBy(how = How.XPATH, using = "//option[text()='Bogota']")
    private WebElement opt_Bogota;

    @FindBy(how = How.XPATH, using = "//li[@class=\"yatri_date input required stringish\" and @id=\"appointments_consulate_appointment_date_input\"]")
    private WebElement opt_Date;

    @FindBy(how = How.XPATH, using = "//div[@class=\"ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-datepicker-multi ui-datepicker-multi-2\"]")
    private WebElement div_Date;

    @FindBy(how = How.XPATH, using = "//span[@class=\"ui-icon ui-icon-circle-triangle-e\"]")
    private WebElement btn_siguiente;

    public HomePage() {
        PageFactory.initElements(getDriver(), this);
    }

    WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));

    public void isInTheHomePage() {
        try {
            isPresent(btn_Continuar);
            btn_Continuar.click();
        } catch (Exception e) {
            System.out.println("-Error: " + e);
        }
    }

    public void reschedule_appointment(){
        try {
            isPresent(lbl_ReprogramarCita);
            lbl_ReprogramarCita.click();
            isPresent(btn_Reprogramar);
            btn_Reprogramar.click();
            isPresent(lbl_Reprogramar_Section);
            isPresent(btn_Continuar_Section);
            btn_Continuar_Section.click();
            isPresent(lbl_cita);
            scrollToElement(lbl_cita);
            sel_ciudad.click();
            isPresent(opt_Bogota);
            opt_Bogota.click();
            Thread.sleep(1000);
            opt_Date.click();
            getAvailableDays(); // Que necesito retornar ?
            //Seleccionar dia
            //Seleccionar la hora

            //Seleccionar ciudad CAS
            //Seleccionar Dia CAS
            //Seleccionar hora CAS

            //Cpntinuar
            //Confirmar
            //Sreenshot


            System.out.println("punto");



        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException | InterruptedException e) {
            System.out.println("-Error: " + e);
        }
    }

    public boolean isPresent(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            Assert.assertTrue(element.isEnabled());
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public WebElement waitToElementoClickeable(String locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
    }

    public WebElement spanByText(String text) {
        String xpath = String.format("//span[text()='%s']", text);
        return waitToElementoClickeable(xpath);
    }

    public void getAvailableDays() {
        try {
            List<WebElement> diasHabilitados;
            int i = 0;
            while (i==0){
                diasHabilitados = getDriver().findElements(By.xpath("//td[@data-handler='selectDay']"));
                System.out.println(diasHabilitados.size());
                i = diasHabilitados.size();
                if (diasHabilitados.size() == 0) {
                    Thread.sleep(1000);
                    btn_siguiente.click();
                }else  {
                    System.out.println(diasHabilitados);
                }
            }
            diasHabilitados = getDriver().findElements(By.xpath("//td[@data-handler='selectDay']"));
            for (WebElement dia : diasHabilitados) {
                String numeroDia = dia.findElement(By.tagName("a")).getText(); // Ej: "12"
                String mes = dia.getAttribute("data-month"); // Ej: "2" → marzo (recuerda: enero = 0)
                String año = dia.getAttribute("data-year");  // Ej: "2027"

                System.out.println("Día habilitado: " + numeroDia + "/" + (Integer.parseInt(mes) + 1) + "/" + año);
            }
        }catch (Exception e) {
            System.out.println("-Error: " + e);
        }
    }
}

