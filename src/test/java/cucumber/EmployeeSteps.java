package cucumber;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.Application.*;
import timeCat.Domain.Employee;
import timeCat.Exceptions.DuplicateException;
import timeCat.Exceptions.InvalidNameException;
import timeCat.Exceptions.NotFoundException;

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
    @When("an employee registers a new employee")
    public void aEmployeeRegistersANewEmployee() {
        try {
            timeCatApp.registerEmployee(employeeHelper.getEmployee().getInitials());
        } catch (InvalidNameException | DuplicateException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("an employee registers a new employee with {int} letter initials")
    public void anEmployeeRegistersANewEmployeeWithLetterInitials(int initialsCount) {
        String employeeInitials = "A".repeat(initialsCount);
        employeeHelper.setEmployee(new Employee(employeeInitials));
        try {
            timeCatApp.registerEmployee(employeeHelper.getEmployee().getInitials());
        } catch (DuplicateException | InvalidNameException e) {
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
            timeCatApp.registerEmployee(employeeHelper.getEmployee().getInitials());
        }
        catch(DuplicateException | InvalidNameException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("an employee registers a new employee with the same initials")
    public void anEmployeeRegistersANewEmployeeWithTheSameInitials() {
        try {
            timeCatApp.registerEmployee(employeeHelper.getEmployee().getInitials());
        } catch (DuplicateException | InvalidNameException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

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

    @Given("an employee is not registered")
    public void anEmployeeIsNotRegistered() {
        theEmployeeIsNotRegistered();
    }

    @When("an employee unregisters the employee")
    public void anEmployeeUnregisteresTheEmployee() {
        try {
            timeCatApp.unregisterEmployee(employeeHelper.getEmployee().getInitials());
        }catch (NotFoundException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

}