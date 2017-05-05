Feature: As a user I want to different types of references
    
    Scenario: user adds a book reference successfully
        Given add references is selected
        When valid book reference information is entered
        Then a new reference is added

    Scenario: user adds inproceeding reference successfully
        Given add references is selected
        When valid inproceedings reference information is entered
        Then a new reference is added