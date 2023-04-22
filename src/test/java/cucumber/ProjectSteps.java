package cucumber;

import io.cucumber.java.en.Given;
import timeCat.app.InvalidProjectNameException;
import timeCat.app.ProjectNotFoundException;
import timeCat.app.TimeCatApp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.domain.CostumerProject;
import timeCat.domain.InternalProject;
import timeCat.domain.Project;
import java.util.ArrayList;
import java.util.Calendar;


import static org.junit.Assert.*;

public class ProjectSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private ProjectHelper projectHelper;
    private Project project;

    public ProjectSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage, ProjectHelper projectHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.projectHelper = projectHelper;
    }

    @When("a employee creates a costumer project with the name {string}")
    public void aEmployeeCreatesACostumerProjectWithTheName(String projectName) {
        try {
            timeCatApp.createCostumerProject(projectName);
        } catch (InvalidProjectNameException e ) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("a employee creates a internal project with the name {string}")
    public void aEmployeeCreatesAInternalProjectWithTheName(String projectName) {
        try {
            timeCatApp.createInternalProject(projectName);
        } catch (InvalidProjectNameException e ) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @When("a employee removes a project with the name {string}")
    public void aEmployeeRemoveAProjectWithTheName(String projectName) {
        try {
            Project projectToRemove = timeCatApp.getProjectByName(projectName);
            timeCatApp.removeProject(projectToRemove.getID());
        } catch (ProjectNotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Given("a project with the name {string} is in the project repository")
    public void theProjectWithTheNameIsInTheProjectRepository(String projectName) {
        try {
            timeCatApp.createCostumerProject(projectName);
        } catch (InvalidProjectNameException e ) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    @Then("the project with the name {string} is in the project repository")
    public void aProjectWithTheNameIsInTheProjectRepository(String projectName) {
        ArrayList<Project> projects = timeCatApp.getProjects();
        boolean projectExists = false;
        for (Project project : projects){
            if (project.getName().equals(projectName)){
                projectExists = true;
                this.project = project;
            }
        }
        assertTrue(projectExists);
    }

    @Then("the project with the name {string} is not in the project repository")
    public void aProjectWithTheNameIsNotInTheProjectRepository(String projectName) {
        ArrayList<Project> projects = timeCatApp.getProjects();
        boolean projectExists = false;
        for (Project project : projects){
            if (project.getName().equals(projectName)){
                projectExists = true;
            }
        }
        assertFalse(projectExists);
    }

    @And("the project is a costumer project")
    public void theProjectIsACostumerProject() {
        assertTrue(project instanceof CostumerProject);
    }

    @And("the project is a internal project")
    public void theProjectIsAInternalProject() {
        assertTrue(project instanceof InternalProject);
    }

    @And("the projectID starts with the current year")
    public void theProjectIDStartsWithTheCurrentYear() {
        String currentYearPrefix = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) % 100);
        assertTrue(project.getID().startsWith(currentYearPrefix));
    }

    @And("the project has no project manager")
    public void theProjectHasNoProjectManager() {
        assertNull(project.getPM());
    }

    @And("the project has no activities")
    public void theProjectHasNoActivities() {
        assertTrue(project.getActivities().isEmpty());
    }

    @Then("I get the error message {string}")
    public void iGetTheErrorMessage(String errorMessage) throws Exception {
        assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    }
}
