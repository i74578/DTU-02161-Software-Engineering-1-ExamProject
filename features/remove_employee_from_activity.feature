//@auther Lukas Halberg - s216229
Feature: remove employee from activity
	Description: add employee to activity
	Actors: project manager



Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: remove employee from activity
	Given Employee is added to a activity
	And a project manger exist
	When a project manager removes a employee with initials "xx" from activity "xx"
	Then everything is okay

Scenario: Failure to remove employee from activity
	Given that project manager is Logged In
	And a valid activity exicts
	When a project manager tries to remove an employee with initials "xx" to a activity
	Then the error message "employee failed to be removed from activity" is given

Scenario: Failure to remove employee to activity, unknown activity
	Given that project manager is Logged In
	And a valid activity exists
	When a project manager tries to remove an employee with unkown initials to activity "xx"
	Then the error message "Unkown employee listed" is given

