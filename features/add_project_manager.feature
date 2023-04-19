//@auther Lukas Halberg - s216229
Feature: Add project manager
	Description: Assign a project manager
	Actors: project manager

Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: Add a project manager
	Given a project exist
	And a employee exist
	Then the employee will be assigned project manager

Scenario: No employee exist
	Given a project exist
	And a employee do not exist
	Then the error message "Employee do not exist" is given

Scenario: No project exist
	Given a project don not exist
	And a employee do exist
	Then And the error message "Project do not exist" is given
