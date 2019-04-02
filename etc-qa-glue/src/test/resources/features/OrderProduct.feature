Feature: Search for a product and order online

  Scenario: To search and order a product
    Given Eve launches the online shopping website
    When Eve selects Summer dresses from Women's section
    And Eve quick views 'Printed Chiffon Dress' and adds it to cart with size 'M'
    And Eve wants to continue shopping and checkout to cart summary
    Then Eve is redirected to account registration page upon clicking Proceed to Checkout
    When Eve enters the following information during registration
      | EmailAddress             | FirstName | LastName | Password | AddressLine      | City     | State    | PostalCode | MobileNumber |
      | eve.adams@mailinator.com | Eve       | Adams    | eveadams | 108 Sheppard Ave | New York | New York | 10001      | 123456789    |
    And Eve completes the registration process
    Then Eve sees the Printed Chiffon Dress in the last step of registration process on Payment tab

