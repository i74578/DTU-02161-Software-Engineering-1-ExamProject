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

import java.util.ArrayList;

import static org.junit.Assert.*;

//@author  Benjamin Fríðberg - s224347
public class EmployeeSteps {

    private final TimeCatApp timeCatApp;
    private final ErrorMessage errorMessage;
    private final EmployeeHelper employeeHelper;
    private ArrayList<Employee> employeeList;

    public EmployeeSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage, EmployeeHelper employeeHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.employeeHelper = employeeHelper;
        this.employeeList = new ArrayList<>();
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
    @Given("a employee is not logged in")
    public void AEmployeeIsNotLoggedIn() {
        assertFalse(timeCatApp.IsEmployeeLoggedIn());
    }

    //@author  Benjamin Fríðberg - s224347
    @Then("the new employee with initials {string} is registered")
    public void theNewEmployeeWithInitialsIsRegistered(String initials) {
        assertTrue(timeCatApp.hasEmployee(initials));
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

    //@author  Benjamin Fríðberg - s224347
    @When("the employee requests a employee list")
    public void theEmployeeRequestsAEmployeeList() {
        employeeList = timeCatApp.getEmployees();
    }

    //@author  Benjamin Fríðberg - s224347
    @And("a employee on the list is {string}")
    public void aEmployeeOnTheListIs(String initials) {
        assertTrue(employeeList.stream().anyMatch(p -> p.getInitials().equals(initials)));
    }

    //@author  Benjamin Fríðberg - s224347
    @And("a employee on the list is the employee")
    public void aEmployeeOnTheListIsTheEmployee() {
        String employeeInitials = employeeHelper.getEmployee().getInitials();
        assertTrue(employeeList.stream().anyMatch(p -> p.getInitials().equals(employeeInitials)));
    }
}