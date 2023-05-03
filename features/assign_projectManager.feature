#auther Lukas Halberg - s216229
Feature: Assign a project manager
	Description: Assign a project manager
	Actors: Employee

Scenario: Assign project manager
	Given a project is in the project repository
	And the project does not have a project manager
	And a employee exists
	And the employee is logged in
	And a employee with initials "TOM" exists
	When the employee assigns "TOM" to project manager for the project
	Then the employee "TOM" is assigned project manager of the project

Scenario: Assign project manager, when project manager is already assigned
	Given a project is in the project repository
	And the project has a project manager
	And a employee exists
	And the employee is logged in
	And a employee with initials "TOM" exists
	When the employee assigns "TOM" to project manager for the project
	Then I get the error message "Project manager for this project is already assigned"

Scenario: Assign project manager, to employee who does not exist
	Given a project is in the project repository
	And the project does not have a project manager
	And a employee exists
	And the employee is logged in
	And a employee with initials "TOM" does not exists
	When the employee assigns "TOM" to project manager for the project
	Then I get the error message "The employee is not found"