Feature: Ejemplo de request para cucumber

  @API
  Scenario: Prueba GET al endpoint
    Given I send a GET request to the https://api.github.com URI
    Then I get a 200 status code

  @API
  Scenario:  validar la cantidad de entradas en la respuesta
    Given I send a GET request to the https://jsonplaceholder.typicode.com URI
    Then I validate there are 10 items on the /users endpoint

  @API
  Scenario:  validar que un elemento esta en la respuesta
    Given I send a GET request to the https://jsonplaceholder.typicode.com URI
    Then I validate there is a value: Bret in the /users endpoint

  @API
  Scenario:  validar que un elemento esta en la respuesta
    Given I send a GET request to the https://jsonplaceholder.typicode.com URI
    Then I can validate the nested value: Kattie Turnpike in the response at /users endpoint


  @API
  Scenario:  validar que un elemento esta en la respuesta2
    Given I send a GET request to the https://jsonplaceholder.typicode.com URI
    Then I can validate the nested value: -38.2386 in the response at /users endpoint2