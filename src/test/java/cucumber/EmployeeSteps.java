package cucumber;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.Application.*;
import timeCat.Domain.Activity;
import timeCat.Domain.Employee;
import timeCat.Domain.Project;

import static org.junit.Assert.*;

//@author  Benjamin Fríðberg - s224347
public class EmployeeSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;

    public EmployeeSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
    }

    //@author  Benjamin Fríðberg - s224347
    @When("an employee adds a new employee {string} to the user repository")
    public void aEmployeeAddsANewEmployeeToTheUserRepository(String initials) {
        try {
            timeCatApp.addEmployee(initials);
        } catch (DuplicateException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    @Then("the employee {string} is in the user repository")
    public void theUserIsInTheUserRepository(String initials) {
        Employee employee = null;
        try {
            employee = timeCatApp.getEmployee(initials);
        } catch (UserNotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        assertNotNull(employee);
    }

    //@author  Benjamin Fríðberg - s224347
    @Given("an employee {string} is in the user repository")
    public void anEmployeeIsInTheUserRepository(String initials) {
        try {
            timeCatApp.addEmployee(initials);
        }
        catch(DuplicateException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("an employee removes the employee {string} from the user repository")
    public void anEmployeeRemovesTheEmployeeFromTheUserRepository(String initials) {
        try {
            timeCatApp.removeEmployee(initials);
        }catch(UserNotFoundException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the employee {string} is not in the user repository")
    public void theEmployeeIsNotInTheUserRepository(String initials) {
        Employee employee = null;
        try {
            employee = timeCatApp.getEmployee(initials);
        } catch (UserNotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        assertNull(employee);
    }
}