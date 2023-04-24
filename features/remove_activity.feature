#author: Christian Colberg - s224343
Feature: Create Activity
	Description: An activity is created
	Actors: Employee


	Scenario: Employee removes an activity successfully
		Given a project with the name "Cool project" is in the project repository
		And an activity with the name "Create the GUI" is in the project "Cool project"
		When a employee removes an activity with the name "Create the Gui" in the project "Cool project"
		Then the activity with the name "Create the GUI" is removed

	Scenario: Employee removes an activity from non-existent project
		Given an activity with the name "Create the GUI" is in the project "Cool project"
		When a employee removes an activity with the name "Create the Gui" in the project "Cool project"
		Then I get the error message "Invalid project name"

	Scenario: Employee removes an non-existent activity
		Given a project with the name "Cool project" is in the project repository
		When a employee removes an activity with the name "Create the Gui" in the project "Cool project"
		Then I get the error message "Invalid activity name"



