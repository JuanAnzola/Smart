Automatización de Agendamiento de Clases de Inglés
Este proyecto automatiza el proceso de inicio de sesión y programación de clases en una plataforma educativa usando Java, Selenium WebDriver, Cucumber y Gherkin.

🧰 Tecnologías utilizadas
Java
Selenium WebDriver
Cucumber (BDD)
Gherkin
JUnit
Maven (opcional, confirmar uso)
ChromeDriver
 
```🧪 Estructura del proyecto
    ├── runner/
    │   └── RunCucumberTest.java                # Ejecutable principal
    │
    ├── step_definition/
    │   ├── Hooks.java                          # Manejo del ciclo de vida WebDriver
    │   ├── LoginSteps.java                     # Steps relacionados al login
    │   ├── HomeSteps.java                      # Steps relacionados al home
    │
    ├── page_objects/                           # Carpeta nueva para POM
    │   ├── LoginPage.java                      # Page Object para login
    │   ├── HomePage.java                       # Page Object para home
    │
    ├── utilities/
    │   ├── JsonUtils.java                      # Carga y parseo de JSON
    │   ├── PropertyUtils.java                  # Carga de properties
    │   └── WebDriverUtils.java                 # Manejo de WebDriver
    │
    ├── resources/
    │   ├── configuration/
    │   │   └── application.properties          # Archivo de configuración del entorno 
    │   │
    │   └── data/
    │       └── test_groups.json                # Datos de pruebas (agrupaciones)
    │
    ├── features/
    │   └── Test.feature                        # Escenarios en Gherkin

```

⚙️ Configuración
Antes de ejecutar el proyecto, completa las siguientes variables en el archivo application.properties:
```
app.url=
app.username=
app.password=
app.accountName=
app.classNumber=
```
Además, asegúrate de:
  - Tener chromedriver instalado y agregar su path correctamente en Hooks.java:
    - ```System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chromedriver.exe");```
  
- Tener configurada la ruta del application.properties en el archivo llamado "Property.Utils": 
    - ```private static final String PROPERTIES_FILE = "C:\\...\\...\\src\\test\\resources\\configuration\\application.properties";```
 
