#author  Benjamin Fríðberg - s224347
Feature: Create Project
	Description: A project is removed
	Actors: Employee

Scenario: Employee removes a project successfully
	Given a project with the name "Develop Project management system" is in the project repository
	When a employee remove a project with the name "Develop Project management system"
	Then a project with the name "Create Project management system" is not in the project repository

	
