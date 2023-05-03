package cucumber;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.application.*;
import timeCat.domain.Employee;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

//@author  Benjamin Fríðberg - s224347
public class EmployeeSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private EmployeeHelper employeeHelper;

    public EmployeeSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage, EmployeeHelper employeeHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.employeeHelper = employeeHelper;
    }

    //@author  Benjamin Fríðberg - s224347
    @When("the employee registers a new employee")
    public void aEmployeeRegistersANewEmployee() {
        try {
            timeCatApp.registerEmployee("NEW");
        } catch (InvalidNameException | DuplicateException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //@author  Benjamin Fríðberg - s224347
    @When("an employee registers a new employee with {int} letter initials")
    public void anEmployeeRegistersANewEmployeeWithLetterInitials(int initialsCount) {
        String employeeInitials = "A".repeat(initialsCount);
        employeeHelper.setEmployee(new Employee(employeeInitials));
        try {
            timeCatApp.registerEmployee(employeeHelper.getEmployee().getInitials());
        } catch (DuplicateException | InvalidNameException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    @Then("the employee is registered")
    public void theUserIsRegistered() {
        Employee employee = null;
        try {
            employee = timeCatApp.getEmployee(employeeHelper.getEmployee().getInitials());
        } catch (NotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        assertNotNull(employee);
    }

    //@author  Benjamin Fríðberg - s224347
    @Given("an employee is registered")
    public void anEmployeeIsRegistered() {
        try {
            employeeHelper.registerEmployee(employeeHelper.getEmployee().getInitials());
        }
        catch(Exception e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //@author  Benjamin Fríðberg - s224347
    @When("an employee registers a new employee with the same initials")
    public void anEmployeeRegistersANewEmployeeWithTheSameInitials() {
        try {
            timeCatApp.registerEmployee(employeeHelper.getEmployee().getInitials());
        } catch (DuplicateException | InvalidNameException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //@author  Benjamin Fríðberg - s224347
    @Then("the employee is not registered")
    public void theEmployeeIsNotRegistered() {
        Employee employee = null;
        try {
            employee = timeCatApp.getEmployee(employeeHelper.getEmployee().getInitials());
        } catch (NotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
        assertNull(employee);
    }
    //@author  Benjamin Fríðberg - s224347
    @Given("an employee is not registered")
    public void anEmployeeIsNotRegistered() {
        theEmployeeIsNotRegistered();
    }
    //@author  Benjamin Fríðberg - s224347
    @When("an employee unregisters the employee")
    public void anEmployeeUnregisteresTheEmployee() {
        try {
            timeCatApp.unregisterEmployee(employeeHelper.getEmployee().getInitials());
        }catch (NotFoundException | NotAllowedException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    @Given("that a employee is logged in")
    public void thatAEmployeeIsLoggedIn() throws Exception {
        String employeeInitials = employeeHelper.registerTestEmployee();
        timeCatApp.login(employeeInitials);
    }
    //@author  Benjamin Fríðberg - s224347
    @Given("that a employee is not logged in")
    public void thatAEmployeeIsNotLoggedIn() {
        assertFalse(timeCatApp.IsEmployeeLoggedIn());
    }

    //@author  Benjamin Fríðberg - s224347
    @Then("the new employee is registered")
    public void theNewEmployeeIsRegistered() {
        assertTrue(timeCatApp.hasEmployee("NEW"));
    }
    //@author  Benjamin Fríðberg - s224347
    @Given("an employee is with initials {string} is registered")
    public void anEmployeeIsWithInitialsIsRegistered(String initials) throws Exception {
        employeeHelper.registerEmployee(initials);
    }
    //@author  Benjamin Fríðberg - s224347
    @When("the employee registers a new employee with the initials {string}")
    public void theEmployeeRegistersANewEmployeeWithTheInitials(String initials) {
        try {
            timeCatApp.registerEmployee(initials);
        } catch (DuplicateException | InvalidNameException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }

    }
    //@author  Benjamin Fríðberg - s224347
    @And("an employee with initials {string} is not registered")
    public void anEmployeeWithInitialsIsNotRegistered(String initials) {
        assertFalse(timeCatApp.hasEmployee(initials));

    }
    //@author  Benjamin Fríðberg - s224347
    @When("the employee unregisters the employee with initials {string}")
    public void theEmployeeUnregistersTheEmployeeWithInitials(String initials) {
        try {
            timeCatApp.unregisterEmployee(initials);
        } catch (NotFoundException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
}