//@author  Benjamin Fríðberg - s224347
Feature: Create Project
	Description: A project is created
	Actors: Employee

Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: Create a project successfully
 	Given Employee is Logged In
 	When the project is created
 	Then a project with the name "test", author "Kent Beck", and signature "Beck99" is contained in the library
 	
Scenario: Add a book when the adminstrator is not logged in
	Given that the administrator is not logged in
 	And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
	When the book is added to the library
	Then the error message "Administrator login required" is given
	
