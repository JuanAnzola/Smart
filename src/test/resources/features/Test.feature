Feature: Agendar clase de ingles

  @---
  Scenario: Usuario agenda una clase de inglés exitosamente
    Given El usuario está en la página de inicio de sesión
    When Ingresa sus credenciales válidas y accede a la plataforma
    Given Accede a la seccion de programación y selecciona el plan de estudios
    And Elige una clase disponible
    And Inicia y confirma el agendamiento
    Then Debería ver un mensaje de confirmación de la clase agendada
