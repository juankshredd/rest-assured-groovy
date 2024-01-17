@BraveNewCoin
  Feature: BraveNewCoin API scenarios

    Rule: When I send a POST request to the endpoint, I receive a token I can use to make further authenticated calls.

        Scenario: As a user I can retrieve a token when making a
          Given I have a valid API key for the https://bravenewcoin.p.rapidapi.com URI
          When I send a POST request with a valid body payload to the /oauth/token endpoint
          Then I can validate I receive a valid token in the response

        Scenario: As a user, when I use an invalid API key, I get an HTTP code status 401
          Given I have an invalid API key for the https://bravenewcoin.p.rapidapi.com URI
          When I send a POST request with a valid body payload to the /oauth/token endpoint
          Then I can validate I receive a HTTP Status 403