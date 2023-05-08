#author  Benjamin Fríðberg - s224347
Feature: List projects
	Description: List projects
	Actors: Employee

Scenario: Employee requests projects list successfully
	Given a costumer project with the name "Project A" is in the project repository
	And a costumer project with the name "Project B" is in the project repository
	And a internal project with the name "Project C" is in the project repository
	And a employee exists
	And the employee is logged in
 	When the employee requests a project list
	Then there are 3 projects on the project list
	And a project with the name "Project A" is on the list
	And a project with the name "Project B" is on the list
	And a project with the name "Project C" is on the list

Scenario: Employee requests projects list successfully
	Given there are non projects in the project repository
	And a employee exists
	And the employee is logged in
	When the employee requests a project list
	Then there are 0 projects on the project list

	
