Feature: Insurance Portal Testing

  @Agent
  Scenario: Agent registers and logs in
    Given User is on the insurance portal homepage
    When User navigates to "Agent" and registers with "agent@example.com" and "password123"
    Then Close the browser

  @Admin
  Scenario: Admin registers and logs in
    Given User is on the insurance portal homepage
    When User navigates to "Admin" and registers with "admin@example.com" and "password123"
    Then Close the browser

  @PolicyCreation
  Scenario: Agent creates a policy
    Given User is on the insurance portal homepage
    When User logs in as "Agent" with "agent@example.com" and "password123"
    And Agent creates a new policy with "John Doe" and "5000"
    Then Close the browser

  @PolicyValidation
  Scenario: Admin validates the policy
    Given User is on the insurance portal homepage
    When User logs in as "Admin" with "admin@example.com" and "password123"
    Then Admin should see the policy for "John Doe" in the list
    Then Close the browser