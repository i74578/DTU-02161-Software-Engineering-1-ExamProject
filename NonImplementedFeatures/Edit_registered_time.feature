#author  Emil Sundberg - s224337

Feature: Edit an already time record.
	Description: The ability to edit your own submitted time records.
	Actors: Employee
	
Scenario: Edit time record
	Given Employee is logged in
	And there is submitted one or more time records
	When the user updates a time record
	Then the time record gets updated.

Scenario: failure to edit time record
	Given that the Employee is logged in
	And there is not any time records
	Then the Employee is unable to edit any time record
	And the error message "There is not any time records avaliable" is given
