#author  Benjamin Fríðberg - s224347
Feature: Remove Project
	Description: A project is removed
	Actors: Employee

Scenario: Employee removes a project successfully
	Given a project is in the project repository
	And a employee exists
	And the employee is logged in
	And the employee is assigned project manager for the project
	When the employee removes the project
	Then the project is not in the project repository

Scenario: Employee removes a project which does not exist
	Given the project with the name "Non existing project" is not in the project repository
	When a employee removes a project with the name "Non existing project"
	Then I get the error message "The project is not found"