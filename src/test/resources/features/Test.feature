Feature: Agendar clase de ingles

  @---
  Scenario: Usuario agenda una clase de inglés exitosamente
    Given El usuario está en la página de inicio de sesión
    When Ingresa sus credenciales válidas y accede a la plataforma
    Then Debería ser redirigido al home
    Given El usuario está en la sección de programación
    When Selecciona el plan de estudios
    And Elige una clase disponible
    And Inicia y confirma el agendamiento
    Then Debería ver un mensaje de confirmación de la clase agendada

    #Given Log in to the page and access the personal account
    #And Delete all the unwanted emails
    #And Search the unwanted emails
    #When Check that there are no more unwanted emails
    #Then Log out of the personal account