Feature: As a user I want to add references

    Scenario: user adds a reference successfully
       Given add references is selected
       When  valid reference information is entered
       Then  a new reference is added

    Scenario: user tries to add an invalid reference
        Given add references is selected
        When  invalid reference information is entered
        Then  reference is not added
