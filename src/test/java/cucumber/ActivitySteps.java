//#author: Christian Colberg - s224343
package cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.Application.*;
import timeCat.Domain.*;
import timeCat.Exceptions.NotFoundException;
import timeCat.Exceptions.DuplicateException;
import timeCat.Exceptions.InvalidNameException;


import static org.junit.Assert.*;

public class ActivitySteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;

    Activity activity = null;

    public ActivitySteps(TimeCatApp timeCatApp, ErrorMessage errorMessage) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
    }

    //#author: Christian Colberg - s224343
    @Given("a project with the name {string} is not in the project repository")
    public void aProjectWithTheNameIsNotInTheProjectRepository(String projectName) {
        assertFalse(timeCatApp.hasProject(projectName));
    }

    //#author: Christian Colberg - s224343
    @And("a activity with the name {string} is in the project {string}")
    public void anActivityWithTheNameIsInTheProject(String activityName, String projectName) throws Exception {
        String projectID = timeCatApp.getProjectByName(projectName).getID();
        timeCatApp.createActivity(activityName, projectID);
    }

    //#author: Christian Colberg - s224343
    @When("a employee creates an activity with the name {string} in the project {string}")
    public void aEmployeeCreatesAnActivityWithTheNameInTheProject(String activityName, String projectName)  {
        try {
            String projectID = timeCatApp.getProjectByName(projectName).getID();
            timeCatApp.createActivity(activityName, projectID);
        } catch (NotFoundException | InvalidNameException | DuplicateException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //#author: Christian Colberg - s224343
    @When("a employee removes an activity with the name {string} in the project {string}")
    public void aEmployeeRemovesAnActivityWithTheNameInTheProject(String activityName, String projectName)  {
        try {
            Project project = timeCatApp.getProjectByName(projectName);
            Activity activity = project.getActivityByName(activityName);
            timeCatApp.removeActivity(activity.getActivityID(), project.getID());
        }
        catch(NotFoundException e){
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //#author: Christian Colberg - s224343
    @Then("the activity with the name {string} is not in the project {string}")
    public void the_activity_with_the_name_is_not_in_the_project(String activityName, String projectName) throws Exception {
        assertFalse(timeCatApp.getProjectByName(projectName).hasActivity(activityName));
    }

    //#author: Christian Colberg - s224343
    @Then("the activity with the name {string} is in the project {string}")
    public void theActivityWithTheNameIsInTheProject(String activityName, String projectName) throws Exception {
        this.activity = timeCatApp.getProjectByName(projectName).getActivityByName(activityName);
        assertNotNull(this.activity);
    }

    //#author: Christian Colberg - s224343
    @And("the activity has no registered hours")
    public void theActivityHasNoRegisteredHours() {
        assertEquals(this.activity.getTimesheet().getEntryCount(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the activity has no assigned employees")
    public void theActivityHasNoAssignedEmployees() {
        assertTrue(this.activity.getAssignedEmployees().isEmpty());
    }

    //#author: Christian Colberg - s224343
    @And("the startWeek is not assigned")
    public void theStartWeekIsNotAssigned() {
        assertEquals(this.activity.getStartWeek(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the hourEstimate is not assigned")
    public void theHourEstimateIsNotAssigned() {
        assertEquals(this.activity.getHourEstimate(), 0);
    }

    //#author: Christian Colberg - s224343
    @And("the weekDuration is not assigned")
    public void theWeekDurationIsNotAssigned() {
        assertEquals(this.activity.getWeekDuration(), 0);
    }
}