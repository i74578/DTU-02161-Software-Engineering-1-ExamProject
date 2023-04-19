Feature: Add Book
	Description: A project is created
	Actors: Employee

Scenario: Create a project successfully
 	Given that the employee is logged in
 	When the project is created
 	Then a project with the name "test", author "Kent Beck", and signature "Beck99" is contained in the library
 	
Scenario: Add a book when the adminstrator is not logged in
	Given that the administrator is not logged in
 	And there is a book with title "Extreme Programming", author "Kent Beck", and signature "Beck99"
	When the book is added to the library
	Then the error message "Administrator login required" is given
	
