#author  Benjamin Fríðberg - s224347
Feature: Remove employee
	Description: Unregister employee from repo
	Actors: Employee

Scenario: Employee unregisters a employee successfully
	Given that a employee is logged in
	And an employee is registered
 	When an employee unregisters the employee
 	Then the employee is not registered

Scenario: Employee unregisters a non existing employee
	Given that a employee is logged in
	And an employee with initials "TOM" is not registered
	When the employee unregisters the employee with initials "TOM"
	Then I get the error message "The user is not found"

	
