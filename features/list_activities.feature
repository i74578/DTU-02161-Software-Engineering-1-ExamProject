#author  Benjamin Fríðberg - s224347
Feature: List Activities of project
	Description: Lists all activities in project
	Actors: Employee

Scenario: Employee requests activity list successfully
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is logged in
 	When the employee requests a activity list for the project
	Then there are 1 activities on the activity list
	And the activity is in the list

Scenario: Employee requests activity list for non existing project
	Given a project is not in the project repository
	And a employee exists
	And the employee is logged in
	When the employee requests a activity list for the project
	Then I get the error message "The project is not found"

	
