//@author  Benjamin Fríðberg - s224347
Feature: Employee login/logout
	Description: A employee logs in or out
	Actor: Employee

Scenario: Employee can login successfully
	Given that the employee is not logged in
	And the employee initials are "XX"
	And the password is "Password1"
	Then the login succeeds
	And the employee is logged in

Scenario: Employee logs in with wrong password
	Given that the employee is not logged in
	And the employee initials are "XX"
	And the password is "Password2"
	Then the login fails
	And the administrator is not logged in

Scenario: Employee logs out
	Given that the employee is logged in
	When the employee logs out
	Then the employee is not logged in
