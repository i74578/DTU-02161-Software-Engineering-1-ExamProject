
//@auther Lukas Halberg - s216229
Feature: Add project manager
	Description: Assign a project manager
	Actors: project manager


Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in



Scenario: Add a project manager
	Given A project exist
	And A employee exist
	When a employee assignes aa project manager to a project
	Then the project manager will be assigned


Scenario: add a project manager who dosent exist
	Given A project exist
	And A employee dosnt exist
	When a employee assignes a project manager to a project
	Then the project manager will fail





	Scenario: Can't unregister a user that is not registered
	Given a user is not registered with the library
	And that the administrator is logged in
	When the administrator unregisters that user
	Then the error message "User not registered" is given

