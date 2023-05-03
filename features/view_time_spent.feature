//author: Christian Colberg - s224343


Feature: View registered time on activity
	Description: The employee views registered time spent on activity
	Actors: Employee


Scenario: View registered time
	Given that a employee is logged in
	Given employee has registered hours on activity
	When employee views hours spent on activity
	Then hours spent is displayed

Scenario: View registered time, with no time spent
	Given employee is logged in
	Given employee has no registered hours on activities
	When employee views hours spent on activity
	Then I get 0 hours spent

Scenario: View registered time, without being logged in
	Given that a employee is not logged in
	Given employee has registered hours on activity
	When employee views hours spent on activity
	Then I get the error message "You need to be logged in to perform this action"

