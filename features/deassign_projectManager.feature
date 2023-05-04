#auther Lukas Halberg - s216229
Feature: Deassign a project manager
	Description: Deassign a project manager
	Actors: Employee

Scenario: Project manager deassigns himself as project manager successfully
	Given a project is in the project repository
	And a employee exists
	And the employee is assigned project manager for the project
	And the employee is logged in
	When the employee deassigns the project manager from the project
	Then the employee is assigned project manager of the project

Scenario: Non project manager for project deassigns project manager
	Given a project is in the project repository
	And the project has a project manager
	And a employee exists
	And the employee is logged in
	When the employee deassigns the project manager from the project
	Then I get the error message "You need to be a project manager for this project to perform this action"
	And the project still has a project manager