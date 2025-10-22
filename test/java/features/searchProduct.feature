Feature: Search and Place the order for Products

  Scenario: Search Experience for product search in both home and Offers page
    Given User in on GreenCart Landing page
    When user searched with Shortname "Tom" and extracted actual name of product
    Then user searched for "Tom" shortname in offers page
    And validate product name in offer page matches with Landing Page
    And paso 7 leo
    And paso 8 leo
    And paso 9 leo