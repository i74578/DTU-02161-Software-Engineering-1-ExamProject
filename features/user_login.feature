#author  Benjamin Fríðberg - s224347
Feature: User login
	Description: A employee logs in
	Actors: Employee

Scenario: Employee logs in successfully
	Given no employee is logged in
	And a employee exists
	When the employee logs in
	Then employee is logged in

Scenario: Employee fails to login with non existing initials
	Given no employee is logged in
	And an employee is not registered
	When the employee logs in
	Then no employee is logged in
	And I get the error message "The user is not found"

Scenario: Employee already logged in
	Given a employee with initials "TOM" exists
	And a employee with initials "TOM" is logged in
	And a employee with initials "BOB" exists
	When the employee logs in with the initials "BOB"
	Then employee with initials "TOM" is logged in
	And I get the error message "A user is already logged in"
