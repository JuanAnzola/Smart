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
 │
 │   ├── RunCucumberTest        # Ejecuctable
 │
 ├── step_definition/
 │   ├── Hooks.java             # Manejo del ciclo de vida del WebDriver
 │   ├── LoginPage.java         # Lógica de inicio de sesión
 │   ├── HomePage.java          # Lógica de programación de clases
 │
 ├── utilities/
 │   └── PropertyUtils.java     # Carga de propiedades desde application.properties
 │
 ├── resources/
 │   └── application.properties # Archivo de configuración del entorno
 │
 ├── features/
 │   └── Test.feature           # Descripcion de los ecenarios
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
 
