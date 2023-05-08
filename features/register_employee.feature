#author  Benjamin Fríðberg - s224347
Feature: Add employee
	Description: Register employee
	Actors: Employee

Scenario: Employee registers a new employee to the user repository
	Given a employee exists
	And the employee is logged in
	When the employee registers a new employee with the initials "TOM"
 	Then the new employee with initials "TOM" is registered

Scenario: Employee registers a new employee with blank initials to the user repository
	Given a employee exists
	And the employee is logged in
	When an employee registers a new employee with 0 letter initials
	Then I get the error message "Invalid initials length"

Scenario: Employee registers a new employee with 5 letter initials to the user repository
	Given a employee exists
	And the employee is logged in
	When an employee registers a new employee with 5 letter initials
	Then I get the error message "Invalid initials length"

Scenario: Employee registers a employee which already exists
	Given a employee exists
	And the employee is logged in
	Given an employee is with initials "TOM" is registered
	When the employee registers a new employee with the initials "TOM"
	Then I get the error message "The user could not be registered"