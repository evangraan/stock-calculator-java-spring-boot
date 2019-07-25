Feature: Evaluate Expression
  As a client
  In order to make good investment decisions
  When I have a security symbol
  I want to use expressions to interact with available security facts
  
  Scenario: Using attributes in expressions
    Given a security symbol
    And a list of attributes
    When I provide an expression using the attributes
    Then the service looks up the symbol
    And the service interpolates the attribute values
    And the service evaluates the expression to a meaningful result
    