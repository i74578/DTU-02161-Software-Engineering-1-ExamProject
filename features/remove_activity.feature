#author: Christian Colberg - s224343
Feature: Remove Activity
	Description: An activity is created
	Actors: Project Manager


Scenario: Employee removes an activity successfully
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is assigned project manager for the project
	And the employee is logged in
	When the employee removes the activity from the project
	Then the activity is not in the project

Scenario: Employee removes an activity from non-existent project
	Given the project with the name "Project B" is not in the project repository
	And a employee exists
	And the employee is logged in
	When a employee removes an activity from project "Project B"
	Then I get the error message "The project is not found"

Scenario: Employee removes an non-existent activity
	Given a project is in the project repository
	And a employee exists
	And the employee is assigned project manager for the project
	And the employee is logged in
	And the activity is not in the project
	When a employee removes the activity from the project
	Then I get the error message "The activity is not found"



