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
 	When a project manager assigns en employee with initials "xx" to activity "xx"
	Then everything is okay
 	
Scenario: Failure to add employee to activity, unknown activity
	Given that project manager is Logged In
 	And a valid project exicts
	When a project manager tries to assign an employee with initials "xx" to a unkown activity
	Then the error message "Unkown Acitivity listed" is given

Scenario: Failure to add employee to activity, unkown employee
	Given that project manager is Logged In
	And a valid activity exicts
	When a project manager tries to assign an employee with unkown initials to activity "xx"
	Then the error message "Unkown employee listed" is given
