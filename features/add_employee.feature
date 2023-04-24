#author  Benjamin Fríðberg - s224347
Feature: Add employee
	Description: Add employee to repo
	Actors: Employee

Scenario: Employee adds a new employee to the user repository
 	When a employee adds a new employee "Bob" to the user repository
 	Then the user "Bob" is in the user repository

	
