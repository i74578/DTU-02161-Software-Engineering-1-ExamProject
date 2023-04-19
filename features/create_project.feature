//@author  Benjamin Fríðberg - s224347
Feature: Create Project
	Description: A project is created
	Actors: Employee

Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: Employee creates a costumer project successfully
 	Given Employee is Logged In
 	When the a employee creates a costumer project with the name "Program a very nice GUI"
 	Then a costumer project with the name "Program a very nice GUI" is in the project repository

Scenario: Employee creates a internal project successfully
	Given Employee is Logged In
	When the a employee creates a internal project with the name "Replace the light bulb"
	Then a internal project with the name "Replace the light bulb" is in the project repository

Scenario: Employee creates a project with a blank name
	Given Employee is Logged In
	When the a employee creates a internal project with the name ""
	Then a internal project with the name "" is not in the project repository

Scenario: Not logged in Employee tries to create a costumer project
	Given Employee is not logged in
	When the a employee creates a costumer project with the name "Program a very nice GUI"
	Then a costumer project with the name "Program a very nice GUI" is not in the project repository

	
