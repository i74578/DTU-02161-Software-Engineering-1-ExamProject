#author  Benjamin Fríðberg - s224347
Feature: Create Project
	Description: A project is created
	Actors: Employee

Scenario: Employee creates a costumer project successfully
 	When a employee creates a costumer project with the name "Program a very nice GUI"
 	Then a project with the name "Program a very nice GUI" is in the project repository
	And the project is a costumer project
	And the projectID starts with the current year
	And the project has no project manager
	And the project has no activities

Scenario: Employee creates a internal project successfully
	When a employee creates a internal project with the name "Replace the light bulb"
	Then a project with the name "Replace the light bulb" is in the project repository
	And the project is a internal project
	And the projectID starts with the current year
	And the project has no project manager
	And the project has no activities

Scenario: Employee creates a project with a blank name
	When a employee creates a internal project with the name ""
	Then I get the error message "Invalid project name"

	
