//Author: Christian Colberg - s224343

Feature: Pay fine
	Description: The user pays his fines
	Actors: User

Scenario: View registered time
	Given employee is assigned activity/activities
	Given employee has registered hours on activity
	When employee views hours spent on activity
	Then hours spent is displayed

Scenario: View registered time, with no time spent
	Given employee is assigned activity/activities
	Given employee has not registerd


Scenario: Pay fine for overdue books
	Given a user has an overdue book
	Then the user has to pay a fine of 100 DKK
	When the user returns the book
	And the user pays 100 DKK
	Then the user can borrow books again
	And the user has to pay no fine
	
Scenario: Pay fine and then the user has another book which is overdue
	Given a user has an overdue book
	Then the user has to pay a fine of 100 DKK
	When the user returns the book
	And the user pays 100 DKK
	Then the user can borrow books again
	Given the user has another overdue book
	Then the user has to pay a fine of 100 DKK
	When the user returns the book
	And the user pays 100 DKK
	Then the user can borrow books again
	And the user has to pay no fine
	
Scenario: Pay partial fine for overdue books
	Given a user has an overdue book
	Then the user has to pay a fine of 100 DKK
	When the user returns the book
	And the user pays 50 DKK
	Then the user cannot borrow books
	And the user has to pay a fine of 50 DKK
	When the user pays 50 DKK
	Then the user can borrow books again
	And the user has to pay no fine
	