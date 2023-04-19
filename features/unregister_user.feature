//@auther Lukas Halberg - s216229
Feature: Add project manager
	Description: Assign a project manager
	Actors: project manager

Scenario: Add a project manager
	Given a employee is registered
	And the project manager have the role
	When the project manager is registered
	Then the project manager is assigned as project manager

Scenario: Need to be administrator to unregister a user
	Given a user is registered with the library
	And that the administrator is not logged in
	When the administrator unregisters that user
	Then the user is still registered with the library
	And the error message "Administrator login required" is given

Scenario: Can't unregister a user that is not registered
	Given a user is not registered with the library
	And that the administrator is logged in
	When the administrator unregisters that user
	Then the error message "User not registered" is given

