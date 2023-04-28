#author  Benjamin Fríðberg - s224347
Feature: Remove employee
	Description: Unregister employee from repo
	Actors: Employee

Scenario: Employee unregisters a employee successfully
	Given an employee is registered
 	When an employee unregisters the employee
 	Then the employee is not registered

Scenario: Employee unregisters a non existing employee
	Given an employee is not registered
	When an employee unregisters the employee
	Then I get the error message "The user is not found"

	
