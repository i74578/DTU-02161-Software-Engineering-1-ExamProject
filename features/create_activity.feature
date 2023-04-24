#author: Christian Colberg - s224343
Feature: Create Activity
	Description: An activity is created
	Actors: Employee


Scenario: Employee creates an activity successfully
	Given a project with the name "Cool project" is in the project repository
	And an activity with the name "Create the GUI" isn't in the project "Cool project"
	When a employee creates an activity with the name "Create the GUI" in the project "Cool project"
	Then the activity with the name "Create the GUI" is in the project "Cool project"
	And the activity has no registered hours
	And the activity has no assigned employees
	And the startWeek is not assigned
	And the hourEstimate is not assigned
	And the weekDuration is not assigned

Scenario: Employee creates an activity under non-existent project
	Given a project with the name "Project A" is not in the project repository
	When a employee creates an activity with the name "Activity A" in the project "Project A"
	Then I get the error message "The project is not found"

Scenario: Employee creates a duplicate activity
	Given a project with the name "Project B" is in the project repository
	Given an activity with the name "Activity B" is in the project "Project B"
	When a employee creates an activity with the name "Activity B" in the project "Project B"
	Then I get the error message "Duplicate activity"

Scenario: Employee creates an activity with a blank name
	Given a project with the name "Project C" is in the project repository
	When a employee creates an activity with the name "" in the project "Project C"
	Then I get the error message "Invalid activity name"


