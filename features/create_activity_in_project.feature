#auther Lukas Halberg - s216229
Feature: create how much time is going to be spend on project
	Description: create time in project
	Actors: project manager

Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: Employee creates a project successfully
 	Given Employee is Logged In
 	When the a employee creates a project
 	Then a project is in the project repository

Scenario: Employee creates a project successfully
	Given Employee is Logged In
	When the a employee creates a activity with in the project
	Then a employee can work on the activity in the project

Scenario: Employee creates a project with no activity
	Given Employee is Logged In
	When A employee creates project with no activity
	Then no employee can be assigned to the activity

Scenario: No project i created, but employee try to created activity in project
	Given Employee is not logged in
	And employee try to create activity in project
	Then no activity will be created because it is no assigned to any project

	
