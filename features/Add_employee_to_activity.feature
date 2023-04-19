//@author  Emil Sundberg - s224337
Feature: Assign employee to activity
	Description: Assign an employee to an activity
	Actors: Project manager

Background: Employee is logged in
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: Add employee to activity
 	Given Employee is Logged In
 	And a valid project exicts
	And Employee is project manager on project "xx"
 	When Employee assigns en employee with initials "xx" to activity "xx"
	Then Employee is added to activity
 	
Scenario: Failure to add employee to activity, unknown activity
	Given Employee is Logged In
 	And a valid project exicts
	And Employee is project manager on project "xx"
	When Employee tries to assign an employee with initials "xx" to a unkown activity
	Then the error message "Unkown Acitivity listed" is given

Scenario: Failure to add employee to activity, unknown employee
	Given Employee is Logged In
	And a valid activity exists
	And Employee is project manager on project "xx"
	When Employee tries to assign an employee with unkown initials to activity "xx"
	Then the error message "Unkown employee listed" is given

Scenario: Failure to add employee to activity, not project manager
	Given Employee is logged in
	And a valid activity exists
	When Employee tries to assign employee with initials "xx" to activity "xx"
	Then the error message "insufficient permission" is given