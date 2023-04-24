#author  Benjamin Fríðberg - s224347
Feature: Remove employee
	Description: Remove employee from repo
	Actors: Employee

Scenario: Employee removes a employee successfully
	Given an employee "Bob" is in the user repository
 	When an employee removes the employee "Bob" from the user repository
 	Then the employee "Bob" is not in the user repository

Scenario: Employee removes a non existing employee
	When an employee removes the employee "Bob" from the user repository
	Then I get the error message "The user is not found"

	
