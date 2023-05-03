//author: Christian Colberg - s224343
//author: Lukas Halberg - s216229
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

import java.util.ArrayList;
import java.util.Calendar;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TimeSteps {

    private TimeCatApp timeCatApp;
    private ErrorMessage errorMessage;
    private ArrayList<TimesheetEntry> registreredHours;
    private EmployeeHelper employeeHelper;
    private ProjectHelper projectHelper;
    private ActivityHelper activityHelper;
    //author: Lukas Halberg - s216229
    public TimeSteps(TimeCatApp timeCatApp, ErrorMessage errorMessage,EmployeeHelper employeeHelper,ProjectHelper projectHelper,ActivityHelper activityHelper) {
        this.timeCatApp = timeCatApp;
        this.errorMessage = errorMessage;
        this.registreredHours = new ArrayList<>();
        this.employeeHelper = employeeHelper;
        this.projectHelper = projectHelper;
        this.activityHelper = activityHelper;
    }
    //author: Lukas Halberg - s216229
    @When("the employee registers hours spent on activity")
    public void theEmployeeRegistersHoursSpentOnActivity() {

        registreredHours.add(new TimesheetEntry(Calendar.getInstance(),employeeHelper.getEmployee(),2));
        registreredHours.add(new TimesheetEntry(Calendar.getInstance(),employeeHelper.getEmployee(),4));

        try{
            for(TimesheetEntry entry : registreredHours){
                timeCatApp.registerTime(projectHelper.getProject().getID(),activityHelper.getActivity().getActivityID(),entry.getDate(),entry.getHours());
            }
        } catch(InvalidNameException | NotFoundException e){
            errorMessage.setErrorMessage(e.getMessage());
        }

    }

    //author: Lukas Halberg - s216229
    @Then("the hours are registered")
    public void theHoursAreRegistered() throws InvalidNameException, NotFoundException {
        Project project = timeCatApp.getProjectByID(projectHelper.getProject().getID());
        Activity activity = project.getActivityByID(activityHelper.getActivity().getActivityID());
        Timesheet timesheet = activity.getTimesheet();
        ArrayList<TimesheetEntry> timesheetEntries = timesheet.getEntries();
        assertEquals(timesheetEntries, registreredHours);

    }

}
