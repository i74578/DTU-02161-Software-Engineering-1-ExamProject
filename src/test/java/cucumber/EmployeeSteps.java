package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.Application.ActivityNotFoundException;
import timeCat.Application.InvalidActivityNameException;
import timeCat.Application.ProjectNotFoundException;
import timeCat.Application.TimeCatApp;
import timeCat.Domain.Activity;
import timeCat.Domain.Employee;
import timeCat.Domain.Project;

import static org.junit.Assert.*;

public class EmployeeSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;

    public EmployeeSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
    }

    @When("a employee adds a new employee {string} to the user repository")
    public void aEmployeeAddsANewEmployeeToTheUserRepository(String initials) {
        timeCatApp.addEmployee(initials);
    }

    @Then("the user {string} is in the user repository")
    public void theUserIsInTheUserRepository(String initials) {
        Employee employee;
        try {
            employee = timeCatApp.getUser(initials);
        } catch (ProjectNotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        assertNotNull(employee);
    }
}