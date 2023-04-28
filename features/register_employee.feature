#author  Benjamin Fríðberg - s224347
Feature: Add employee
	Description: Register employee
	Actors: Employee

Scenario: Employee registers a new employee to the user repository
 	When an employee registers a new employee
 	Then the employee is registered

Scenario: Employee registers a new employee with 5 letter initials to the user repository
	When an employee registers a new employee with 5 letter initials
	Then I get the error message "Invalid initials"

Scenario: Employee registers a employee which already exists
	Given an employee is registered
	When an employee registers a new employee with the same initials
	Then I get the error message "The user could not be registered"