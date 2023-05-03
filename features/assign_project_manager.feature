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

#Scenario: No employee exist
#	Given a project exist
#	And a employee do not exist
#	Then the error message "Employee do not exist" is given
#
#Scenario: No project exist
#	Given a project don not exist
#	And a employee do exist
#	Then And the error message "Project do not exist" is given

