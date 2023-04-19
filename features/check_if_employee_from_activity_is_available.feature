//@auther Lukas Halberg - s216229
Feature: check if employee is available
	Description: check if employee is available
	Actors: project manager

Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: check if employee is available
	Given a project exist
	And a project manger exist
	When a project manager checks if a employee with initials "xx" is available
	Then employee is available

Scenario: Failure to check if employee is available
	Given that project manager is Logged In
	And a valid activity exist
	When a project manager tries to add a unknown employee with initials "xx" to a activity
	Then the error message "unknown employee is not available to be added to activity" is given


