#author: Lukas Halberg - s216229

Feature: Register hours spent
	Description: The employee registers time spent on activity
	Actors: Employee
	
Scenario: Employee registers hours spent on activity
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is logged in
	When the employee registers hours spent on activity
	Then the hours are registered

Scenario: Employee registers negative hours
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is logged in
	When the employee registers negative hours spent on activity
	Then I get the error message "Can't register negative hours"

Scenario: Employee registers more than 24 hours for a day
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is logged in
	When the employee registers 25 hours spent on activity in one day
	Then I get the error message "Can't register more than 24 hours a day"

Scenario: Employee registers hours spent on activity that doesn't exist
	Given a project is in the project repository
	And a employee exists
	And the employee is logged in
	And the activity is not in the project
	When the employee registers hours spent on activity
	Then the activity has no registered hours
	And I get the error message "The activity is not found"


