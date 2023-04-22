#auther Emil Sundberg - s224337
Feature: Generate report of remaining work
	Description: generate a report of the remaning work on a specified project.
	Actors: Employee



Background: Employee is Logged In
	When Employee submits valid username and password
	Then Employee should be logged in

Scenario: Generate report
	Given a project manger exist for project "xx"
	And employee is project manager for project "xx"
	When employee askes to generate report for project "xx"
	Then report for remaning work on project "xx" is generated

Scenario: failure to Generate report, unknown project
	Given a project manger exist for project "xx"
	And employee is project manager for project "xx"
	When employee askes to generate report for an unknown project
	Then the error message "Unkown project given" is given

Scenario: failure to Generate report, unknown project
	Given a project manger exist for project "xx"
	When employee askes to generate report for project "xx"
	Then the error message "insufficient permission" is given

