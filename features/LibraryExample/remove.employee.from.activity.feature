//@auther Lukas Halberg - s216229
Feature: remove employee from activity
	Description: add employee to activity
	Actors: project manager



Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: Add employee to activity
	Given Employee is Logged In
	And a valid project exicts
	When a project manager assigns en employee with initials "xx" to activity "xx"
	Then everything is okay

Scenario: Failure to add employee to activity, unknown activity
	Given that project manager is Logged In
	And a valid project exicts
	When a project manager tries to assign an employee with initials "xx" to a unkown activity
	Then the error message "Unkown Acitivity listed" is given

Scenario: Failure to add employee to activity, unknown employee
	Given that project manager is Logged In
	And a valid activity exists
	When a project manager tries to assign an employee with unkown initials to activity "xx"
	Then the error message "Unkown employee listed" is given

