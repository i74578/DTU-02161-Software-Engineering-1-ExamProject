package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.Application.TimeCatApp;
import timeCat.Domain.Employee;
import timeCat.Exceptions.NotAllowedException;
import timeCat.Exceptions.NotFoundException;

import static org.junit.Assert.*;

//@author  Benjamin Fríðberg - s224347
public class LoginSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;

    public LoginSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
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

    //@author  Benjamin Fríðberg - s224347
    @And("a employee with initials {string} exists")
    public void aEmployeeWithInitialsExists(String initials) throws Exception {
        timeCatApp.registerEmployee(initials);
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

    //@author  Benjamin Fríðberg - s224347
    @When("the employee logs in with the initials {string}")
    public void theEmployeeLogsInWithTheInitials(String initials)  {
        try {
            timeCatApp.login(initials);
        } catch (NotFoundException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    @Then("employee with initials {string} is logged in")
    public void employeeWithInitialsIsLoggedIn(String initials) {
        Employee loggedInUser = timeCatApp.GetLoggedInUser();
        assertEquals(loggedInUser.getInitials(),initials);
    }

    @When("the employee logs out")
    public void theEmployeeLogsOut() {
        try {
            timeCatApp.logout();
        } catch (NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
}