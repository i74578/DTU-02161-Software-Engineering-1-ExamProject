package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.application.TimeCatApp;
import timeCat.domain.Employee;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import static org.junit.Assert.*;

//@author  Benjamin Fríðberg - s224347
public class LoginSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private EmployeeHelper employeeHelper;

    public LoginSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage, EmployeeHelper employeeHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.employeeHelper = employeeHelper;
    }



    //@author  Benjamin Fríðberg - s224347
    @Given("no employee is logged in")
    public void thatNoEmployeeIsLoggedIn() {
        assertFalse(timeCatApp.IsEmployeeLoggedIn());
    }

    //@author  Benjamin Fríðberg - s224347
    @Given("a employee with initials {string} is logged in")
    public void aEmployeeWithInitialsIsLoggedIn(String initials) throws Exception {
        timeCatApp.login(initials);
    }

    @And("a employee with initials {string} does not exist")
    public void aEmployeeWithInitialsDoesNotExist(String initials) {
        Employee employee;
        try {
            employee = timeCatApp.getEmployee(initials);
        } catch (NotFoundException e) {
            employee = null;
        }
        assertNull(employee);
    }

    @When("the employee logs in")
    public void theEmployeeLogsIn() {
        try {
            timeCatApp.login(employeeHelper.getEmployee().getInitials());
        } catch (NotFoundException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("employee is logged in")
    public void employeeIsLoggedIn() {
        Employee loggedInUser = timeCatApp.getLoggedInUser();
        assertEquals(loggedInUser.getInitials(),employeeHelper.getEmployee().getInitials());
    }


    @When("the employee logs out")
    public void theEmployeeLogsOut() {
        try {
            timeCatApp.logout();
        } catch (NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }


    @Given("a employee with initials {string} exists")
    public void aEmployeeWithInitialsExists(String initials) throws Exception {
        employeeHelper.registerEmployee(initials);
    }

    @When("the employee logs in with the initials {string}")
    public void theEmployeeLogsInWithTheInitials(String initials) {
        try {
            timeCatApp.login(initials);
        } catch (NotAllowedException | NotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("employee with initials {string} is logged in")
    public void employeeWithInitialsIsLoggedIn(String initials) {
        assertEquals(timeCatApp.getLoggedInUser().getInitials(),initials);
    }
}