#author  Benjamin Fríðberg - s224347
Feature: Create Project
	Description: A project is created
	Actors: Employee

Scenario: Employee creates a costumer project successfully
	Given that a employee is logged in
 	When a employee creates a costumer project with the name "Program a very nice GUI"
 	Then the project with the name "Program a very nice GUI" is in the project repository
	And the project is a costumer project
	And the projectID starts with the current year
	And the project has no project manager
	And the project has no activities

Scenario: Employee creates a internal project successfully
	Given that a employee is logged in
	When a employee creates a internal project with the name "Replace the light bulb"
	Then the project with the name "Replace the light bulb" is in the project repository
	And the project is a internal project
	And the projectID starts with the current year
	And the project has no project manager
	And the project has no activities

Scenario: Employee creates a duplicate costumer project
	Given that a employee is logged in
	And a costumer project with the name "Program a very nice GUI" is in the project repository
	When a employee creates a costumer project with the name "Program a very nice GUI"
	Then I get the error message "Project with the same name already exists"

Scenario: Employee creates a duplicate internal project
	Given that a employee is logged in
	And a internal project with the name "Program a very nice GUI" is in the project repository
	When a employee creates a internal project with the name "Program a very nice GUI"
	Then I get the error message "Project with the same name already exists"

Scenario: Employee creates a project with a blank name
	Given that a employee is logged in
	When a employee creates a internal project with the name ""
	Then I get the error message "Invalid project name"

Scenario: Employee creates a project without being logged in
	Given a employee is not logged in
	When a employee creates a costumer project with the name "Program a very nice GUI"
	Then I get the error message "You need to be logged in to perform this action"
	And the project with the name "Program a very nice GUI" is not in the project repository
