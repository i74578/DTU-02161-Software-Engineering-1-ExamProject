#author: Christian Colberg - s224343


Feature: View registered time on activity
	Description: The employee views registered time spent on activity
	Actors: Employee


Background: Employee is Logged In
When employee submits valid username and password
Then employee should be logged in

Scenario: View registered time
	Given employee is logged in
	Given employee is assigned activity/activities
	Given employee has registered hours on activity
	When employee views hours spent on activity
	Then hours spent is displayed

Scenario: View registered time, with no time spent
	Given employee is logged in
	Given employee is assigned activity/activities
	Given employee has not registered any time on activities
	When employee views hours spent on activity
	Then no hours spent are displayed



