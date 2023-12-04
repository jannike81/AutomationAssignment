Feature: Register a new supporter account

  Scenario Outline: Registrations
    Given I have typed in date of birth
    And I have typed in first name "<username>"
    And I have typed in last name "<lastname>"
    And I have typed in email "<email>"
    And I have typed in confirmation of email "<confEmail>"
    And I have typed in password "<password>"
    And I have typed in confirmation of password "<confPassword>"
    And I have "<action>" the terms and conditions box
    And I have checked the aged over 18 box
    And I have checked the ethics and conduct box
    When I click on the Confirm and join button
    Then I get a text saying "<text>"

    Examples:
      | username | lastname | email      | confEmail  | password      | confPassword  | action  | text                                                                      |
      | Jannike  | Larsson  | @gmail.com | @gmail.com | Password12345 | Password12345 | check   | THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND                 |
      | Jannike  |          | @gmail.com | @gmail.com | Password12345 | Password12345 | check   | Last Name is required                                                     |
      | Jannike  | Larsson  | @gmail.com | @gmail.com | Password12345 | Password54321 | check   | Password did not match                                                    |
      | Jannike  | Larsson  | @gmail.com | @gmail.com | Password12345 | Password12345 | uncheck | You must confirm that you have read and accepted our Terms and Conditions |




















  #Scenario: Successful registration
   # Given I have typed in date of birth
    #And I have typed in first name "Jannike"
    #And I have typed in last name "Larsson"
    #And I have typed in email "@gmail.com"
    #And I have typed in confirmation of email "@gmail.com"
    #And I have typed in password "Password12345"
    #And I have typed in confirmation of password "Password12345"
    #And I have checked the terms and conditions box
    #And I have checked the aged over 18 box
    #And I have checked the ethics and conduct box
    #When I click on the Confirm and join button
    #Then I get a registration confirmation text saying "THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND"

  #Scenario: Missing lastname
    #Given I have typed in date of birth
    #And I have typed in first name "Jannike"
    #And I have typed in email "@gmail.com"
    #And I have typed in confirmation of email "@gmail.com"
    #And I have typed in password "Password12345"
    #And I have typed in confirmation of password "Password12345"
    #And I have checked the terms and conditions box
    #And I have checked the aged over 18 box
    #And I have checked the ethics and conduct box
    #When I click on the Confirm and join button
    #Then I get an error saying "Last Name is required"

  #Scenario: Password doesn't match
    #Given I have typed in date of birth
    #And I have typed in first name "Jannike"
    #And I have typed in last name "Larsson"
    #And I have typed in email "@gmail.com"
    #And I have typed in confirmation of email "@gmail.com"
    #And I have typed in password "Password12345"
    #And I have typed in confirmation of password "Password54321"
    #And I have checked the terms and conditions box
    #And I have checked the aged over 18 box
    #And I have checked the ethics and conduct box
    #When I click on the Confirm and join button
    #Then I get an error saying "Password did not match"

  #Scenario: Terms and condition box is not checked
    #Given I have typed in date of birth
    #And I have typed in first name "Jannike"
    #And I have typed in last name "Larsson"
    #And I have typed in email "@gmail.com"
    #And I have typed in confirmation of email "@gmail.com"
    #And I have typed in password "Password12345"
    #And I have typed in confirmation of password "Password12345"
    #And I have checked the aged over 18 box
    #And I have checked the ethics and conduct box
    #When I click on the Confirm and join button
    #Then I get an error saying "You must confirm that you have read and accepted our Terms and Conditions"