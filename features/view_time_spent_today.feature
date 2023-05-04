#//author: Christian Colberg - s224343


Feature: View registered time on activity
	Description: The employee views registered time spent
	Actors: Employee

Scenario: View registered time successfully
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is logged in
	And employee has registered hours on activity 0 days in the future
	When employee views total hours spent
	Then the registered time is shown in todays time report

Scenario: View registered time, but only has registered time at passed date
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is logged in
	And employee has registered hours on activity -7 days in the future
	When employee views total hours spent
	Then no registered time is shown

Scenario: View registered time, but only has registered time at future date
	Given a project is in the project repository
	And an activity is in the project
	And a employee exists
	And the employee is logged in
	And employee has registered hours on activity 7 days in the future
	When employee views total hours spent
	Then no registered time is shown

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
	And employee has registered hours on activity 0 days in the future
	When employee views total hours spent
	Then I get the error message "You need to be logged in to perform this action"

