#//author: Christian Colberg - s224343


Feature: View registered time on activity
	Description: The employee views registered time spent
	Actors: Employee


Scenario: View registered time
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is logged in
	And employee has registered hours on activity
	When employee views total hours spent
	Then hours spent is displayed

Scenario: View registered time, with no time spent
	Given a employee exists
	And the employee is logged in
	And employee has no registered hours
	When employee views total hours spent
	Then I get 0 hours spent

Scenario: View registered time, without being logged in
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And a employee is not logged in
	And employee has registered hours on activity
	When employee views total hours spent
	Then I get the error message "You need to be logged in to perform this action"

