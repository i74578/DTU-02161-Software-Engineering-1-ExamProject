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
 	Then a project must have a given time set in the project

Scenario: Employee creates a project successfully
	Given Employee is Logged In
	When the a employee creates a project
	And  there is no time registered to the project
	Then the project cant be succesfuly done


	
