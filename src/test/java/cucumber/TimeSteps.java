//author: Christian Colberg - s224343
//author: Lukas Halberg - s216229
package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.application.TimeCatApp;
import timeCat.domain.TimesheetEntry;

import java.util.ArrayList;

import static org.junit.Assert.assertNull;

public class TimeSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private TimeSteps timeSteps;
    private ArrayList<TimesheetEntry> registreredHours;


    public TimeSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.registreredHours = new ArrayList<>();
    }

    @When("the employee registers hours spent on activity")
    public void theEmployeeRegistersHoursSpentOnActivity() {

    }


    @Then("the hours are registered")
    public void theHoursAreRegistered() {
    }

    @And("an employee is assigned activity")
    public void anEmployeeIsAssignedActivity() {
    }

    @And("an employee is not assigned activity")
    public void anEmployeeIsNotAssignedActivity() {
    }











    @Given("employee has registered hours on activity")
    public void employeeHasRegisteredHoursOnActivity() {



    }

    @When("employee views hours spent on activity")
    public void employeeViewsHoursSpentOnActivity() {




    }

    @Then("hours spent is displayed")
    public void hoursSpentIsDisplayed() {




    }



}
