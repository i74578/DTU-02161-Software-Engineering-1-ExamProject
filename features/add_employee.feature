#author  Benjamin Fríðberg - s224347
Feature: Add employee
	Description: Add employee to repo
	Actors: Employee

Scenario: Employee adds a new employee to the user repository
 	When an employee adds a new employee "Bob" to the user repository
 	Then the employee "Bob" is in the user repository

Scenario: Employee adds a employee which already exists
	Given an employee "Bob" is in the user repository
	When an employee adds a new employee "Bob" to the user repository
	Then I get the error message "The user already exists"

	
