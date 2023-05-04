package cucumber;

import io.cucumber.java.en.Given;
import timeCat.domain.*;
import timeCat.exceptions.DuplicateException;
import timeCat.application.TimeCatApp;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

//@author  Benjamin Fríðberg - s224347
public class ProjectSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private ProjectHelper projectHelper;
    private EmployeeHelper employeeHelper;
    private Project project;
    private ArrayList<Project> projectList;

    public ProjectSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage, ProjectHelper projectHelper,EmployeeHelper employeeHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.projectHelper = projectHelper;
        this.employeeHelper = employeeHelper;
        this.projectList = new ArrayList<>();
    }

    //@author  Benjamin Fríðberg - s224347
    @Then("I get the error message {string}")
    public void iGetTheErrorMessage(String errorMessage) throws Exception {
        assertEquals(errorMessage, this.errorMessage.getErrorMessage());
    }

    //@author  Benjamin Fríðberg - s224347
    @Given("a costumer project with the name {string} is in the project repository")
    public void aCostumerProjectWithTheNameIsInTheProjectRepository(String projectName) throws Exception {
        projectHelper.createCostumerProject(projectName);
    }

    //@author  Benjamin Fríðberg - s224347
    @Given("a internal project with the name {string} is in the project repository")
    public void aInternalProjectWithTheNameIsInTheProjectRepository(String projectName) throws Exception {
        projectHelper.createInternalProject(projectName);
    }

    //@author  Benjamin Fríðberg - s224347
    @When("a employee creates a costumer project with the name {string}")
    public void aEmployeeCreatesACostumerProjectWithTheName(String projectName) {
        try {
            timeCatApp.createCostumerProject(projectName);
        } catch (InvalidNameException | DuplicateException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    @When("a employee creates a internal project with the name {string}")
    public void aEmployeeCreatesAInternalProjectWithTheName(String projectName) {
        try {
            timeCatApp.createInternalProject(projectName);
        } catch (InvalidNameException | DuplicateException | NotAllowedException e ) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    @When("a employee removes a project with the name {string}")
    public void aEmployeeRemoveAProjectWithTheName(String projectName) throws Exception {
        try {
            Project projectToRemove = projectHelper.getProjectByName(projectName);
            timeCatApp.removeProject(projectToRemove.getID());
        } catch (NotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }

    //@author  Benjamin Fríðberg - s224347
    @Then("the project with the name {string} is in the project repository")
    public void aProjectWithTheNameIsInTheProjectRepository(String projectName) throws Exception {
        this.project = projectHelper.getProjectByName(projectName);
        assertNotNull(this.project);
    }

    //@author  Benjamin Fríðberg - s224347
    @And("the project is a costumer project")
    public void theProjectIsACostumerProject() {
        assertTrue(project instanceof CostumerProject);
    }

    //@author  Benjamin Fríðberg - s224347
    @And("the project is a internal project")
    public void theProjectIsAInternalProject() {
        assertTrue(project instanceof InternalProject);
    }

    //@author  Benjamin Fríðberg - s224347
    @And("the projectID starts with the current year")
    public void theProjectIDStartsWithTheCurrentYear() {
        String currentYearPrefix = String.valueOf(Calendar.getInstance().get(Calendar.YEAR) % 100);
        assertTrue(project.getID().startsWith(currentYearPrefix));
    }

    //@author  Benjamin Fríðberg - s224347
    @And("the project has no project manager")
    public void theProjectHasNoProjectManager() {
        assertNull(project.getPM());
    }

    //@author  Benjamin Fríðberg - s224347
    @And("the project has no activities")
    public void theProjectHasNoActivities() {
        assertTrue(project.getActivities().isEmpty());
    }


    //@author  Benjamin Fríðberg - s224347
    @And("the project project with name {string} does not have a project manager")
    public void theProjectProjectWithNameDoesNotHaveAProjectManager(String projectName) {

    }

    //@author  Benjamin Fríðberg - s224347
    @Given("a project is in the project repository")
    public void aProjectIsInTheProjectRepository() throws Exception {
        projectHelper.addTestProject();
        project = projectHelper.getProject();
    }

    //@author  Benjamin Fríðberg - s224347
    @And("a employee exists")
    public void aEmployeeExists() throws Exception {
        employeeHelper.registerTestEmployee();
    }

    //@author  Benjamin Fríðberg - s224347
    @And("the employee is assigned project manager for the project")
    public void aEmployeeIsAssignedProjectManagerForTheProject() throws Exception {
        Project project = projectHelper.getProject();
        Employee employee = employeeHelper.getEmployee();
        projectHelper.assignPM(project.getID(),employee.getInitials());

    }

    //@author  Benjamin Fríðberg - s224347
    @When("the employee removes the project")
    public void theEmployeeRemovesTheProject() throws Exception {
        timeCatApp.removeProject(projectHelper.getProject().getID());
    }

    //@author  Benjamin Fríðberg - s224347
    @Then("the project is not in the project repository")
    public void theProjectIsNotInTheProjectRepository() throws Exception {
        assertFalse(timeCatApp.hasProject(projectHelper.getProject().getName()));
    }


    //@author  Benjamin Fríðberg - s224347
    @Then("the project with the name {string} is not in the project repository")
    public void theProjectWithTheNameIsNotInTheProjectRepository(String projectName) throws Exception {
        assertFalse(timeCatApp.hasProject(projectName));
    }
    //@author  Benjamin Fríðberg - s224347
    @And("the employee is logged in")
    public void theEmployeeIsLoggedIn() throws Exception {
        timeCatApp.login(employeeHelper.getEmployee().getInitials());
    }
    //@author  Benjamin Fríðberg - s224347
    @Given("a project is not in the project repository")
    public void aProjectIsNotInTheProjectRepository() throws Exception {
        ArrayList<Project> projects = timeCatApp.getProjects();
        project = projectHelper.getProject();
        String projectID = project.getID();
        assertFalse(projects.stream().anyMatch(p -> p.getID().equals(projectID)));
    }
    //@author  Benjamin Fríðberg - s224347
    @And("the project does not have a project manager")
    public void theProjectDoesNotHaveAProjectManager() {
        assertNull(project.getPM());
    }
    //@author  Benjamin Fríðberg - s224347
    @When("the employee assigns {string} to project manager for the project")
    public void theEmployeeAssignsToProjectManagerForTheProject(String initials) {
        try {
            projectHelper.assignPM(project.getID(),initials);
        } catch (NotAllowedException | NotFoundException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }

    }
    //@author  Benjamin Fríðberg - s224347
    @Then("the employee {string} is assigned project manager of the project")
    public void theEmployeeIsAssignedProjectManagerOfTheProject(String initials) throws Exception {
        String projectID = project.getID();
        Employee projectManager = timeCatApp.getProjectByID(projectID).getPM();
        assertTrue(projectManager == timeCatApp.getEmployee(initials));
    }

    //@author  Benjamin Fríðberg - s224347
    @And("the project has a project manager")
    public void theProjectHasAProjectManager() throws Exception {
        projectHelper.assignTestPM();
    }
    //@author  Benjamin Fríðberg - s224347
    @And("a employee with initials {string} does not exists")
    public void aEmployeeWithInitialsDoesNotExists(String initials) {
        assertFalse(timeCatApp.hasProject(initials));
    }
    //@author  Benjamin Fríðberg - s224347
    @When("the employee deassigns the project manager from the project")
    public void theEmployeeDeassignsTheProjectManagerFromTheProject() {
        try {
            timeCatApp.deassignPM(project.getID());
        } catch (NotFoundException | NotAllowedException e) {
            errorMessage.setErrorMessage(e.getMessage());
        }
    }
    //@author  Benjamin Fríðberg - s224347
    @Then("the employee is assigned project manager of the project")
    public void theEmployeeIsAssignedProjectManagerOfTheProject() throws NotFoundException {
        String projectID = project.getID();
        assertNull(timeCatApp.getProjectByID(projectID).getPM());
    }
    //@author  Benjamin Fríðberg - s224347
    @And("the project still has a project manager")
    public void theProjectStillHasAProjectManager() {
        assertNotNull(project.getPM());
    }

    @When("the employee requests a project list")
    public void theEmployeeRequestsAProjectList() throws Exception {
        projectList = timeCatApp.getProjects();
    }

    @Then("a project with the name {string} is on the list")
    public void aProjectWithTheNameIsOnTheList(String projectName) {
        assertTrue(projectList.stream().anyMatch(p -> p.getName().equals(projectName)));
    }

    @Then("there are {int} projects on the project list")
    public void thereAreProjectsOnTheProjectList(int projectsCount) {
        assertEquals(projectList.size(),projectsCount);
    }

    @Given("there are {int} projects on the project repository")
    public void thereAreProjectsOnTheProjectRepository(int projectCount) throws Exception {
        assertEquals(timeCatApp.getProjects().size(),0);
    }
}
