#author: Christian Colberg - s224343
Feature: Create Activity
	Description: An activity is created
	Actors: Project Manager


Scenario: Employee creates an activity successfully
	Given a project is in the project repository
	And the activity is not in the project
	And a employee exists
	And the employee is assigned project manager for the project
	And the employee is logged in
	When the employee creates the activity in the project
	Then the activity is in the project
	And the activity has no registered hours
	And the activity has no assigned employees

Scenario: Employee creates an activity under non-existent project
	Given a project is not in the project repository
	And a employee exists
	And the employee is logged in
	When the employee creates an activity with the name "Activity A" in the project
	Then I get the error message "The project is not found"

Scenario: Employee creates a duplicate activity
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is assigned project manager for the project
	And the employee is logged in
	When the employee creates the activity in the project
	Then I get the error message "Duplicate activity"

Scenario: Employee creates an activity with a blank name
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is assigned project manager for the project
	And the employee is logged in
	When the employee creates an activity with a blank name in the project
	Then I get the error message "Invalid activity name"


