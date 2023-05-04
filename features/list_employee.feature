#author  Benjamin Fríðberg - s224347
Feature: List employees
	Description: List employees
	Actors: Employee

Scenario: Employee requests employee list successfully
	Given a employee with initials "TOM" exists
	And a employee with initials "JOE" exists
	And a employee exists
	And the employee is logged in
 	When the employee requests a employee list
	Then a employee on the list is the employee
	And a employee on the list is "TOM"
	And a employee on the list is "JOE"

