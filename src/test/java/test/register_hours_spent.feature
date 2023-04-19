//Author: Christian Colberg - s224343

Feature: Register hours spent
	Description: The employee registers time spent on activity
	Actors: Employee

Scenario: Employee registers hours spent on assigned activity
	Given an employee is assigned activity
	When the employee registers hours spent on activity
	Then the hours are registered

Scenario: Employee registers hours spent on unassigned activity
	Given an employee is not assigned the activity
	When the employee registers hours spent on activity
	Then the employee chooses what activity the hours are registered on

