#author: Christian Colberg - s224343
Feature: Remove Activity
	Description: An activity is created
	Actors: Employee


Scenario: Employee removes an activity successfully
	Given a project with the name "Project Aa" is in the project repository
	And a activity with the name "Activity Aa" is in the project "Project Aa"
	When a employee removes an activity with the name "Activity Aa" in the project "Project Aa"
	Then the activity with the name "Activity Aa" is not in the project "Project Aa"

Scenario: Employee removes an activity from non-existent project
	Given the project with the name "Project B" is not in the project repository
	When a employee removes an activity with the name "Activity B" in the project "Project B"
	Then I get the error message "The project is not found"

Scenario: Employee removes an non-existent activity
	Given a project with the name "Project C" is in the project repository
	And the activity with the name "Activity C" is not in the project "Project C"
	When a employee removes an activity with the name "Activity C" in the project "Project C"
	Then I get the error message "The activity is not found"



