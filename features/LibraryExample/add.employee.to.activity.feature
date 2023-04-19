//@auther Lukas Halberg - s216229


Feature: add employee to activity
	Description: Assign a project manager
	Actors: project manager

	Background: Employee is Logged In
		When Employee submits valid username and password
		Then Employee should be logged in

	Scenario: Employee registers hours spent on assigned activity
		Given employee is logged in
		Given an employee is assigned activity
		When the employee registers hours spent on activity
		Then the hours are registered

	Scenario: Employee registers hours spent on unassigned activity
		Given Employee is logged in
		Given an employee is not assigned the activity
		When the employee registers hours spent on activity
		Then the employee chooses what activity the hours are registered on

