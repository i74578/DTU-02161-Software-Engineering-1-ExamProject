#author  Benjamin Fríðberg - s224347
Feature: User logout
	Description: A employee logs out
	Actors: Employee

Scenario: Employee logs out successfully
	Given a employee with initials "BOB" exists
	And a employee with initials "BOB" is logged in
	When the employee logs out
	Then no employee is logged in

Scenario: Employee logs out without being logged in
	Given a employee with initials "BOB" exists
	And no employee is logged in
	When the employee logs out
	Then I get the error message "Can't logout since no user is logged in"
