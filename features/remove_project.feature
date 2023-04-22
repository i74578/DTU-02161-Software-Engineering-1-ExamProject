#author  Benjamin Fríðberg - s224347
Feature: Remove Project
	Description: A project is removed
	Actors: Employee

Scenario: Employee removes a project successfully
	Given a project with the name "Develop Project management system" is in the project repository
	When a employee removes a project with the name "Develop Project management system"
	Then the project with the name "Create Project management system" is not in the project repository

Scenario: Employee removes a project which does not exist
	Given the project with the name "Non existing project" is not in the project repository
	When a employee removes a project with the name "Non existing project"
	Then I get the error message "The project is not found"

	
