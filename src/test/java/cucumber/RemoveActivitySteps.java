package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.Application.ActivityNotFoundException;
import timeCat.Application.InvalidActivityNameException;
import timeCat.Application.ProjectNotFoundException;
import timeCat.Application.TimeCatApp;
import timeCat.Domain.Activity;

import static org.junit.Assert.*;

public class RemoveActivitySteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    Activity activity;

    public RemoveActivitySteps(TimeCatApp timeCatApp, ErrorMessage errorMessage) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
    }

    @And("an activity with the name {string} is in the project {string}")
    public void anActivityWithTheNameIsInTheProject(String activityName, String projectName) {

    }

    @When("a employee removes an activity with the name {string} in the project {string}")
    public void aEmployeeRemovesAnActivityWithTheNameInTheProject(String arg0, String arg1) {

    }

    @Then("the activity with the name {string} is removed")
    public void theActivityWithTheNameIsRemoved(String arg0) {
    }
}