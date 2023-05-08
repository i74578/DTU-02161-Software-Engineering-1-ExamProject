package timeCat.application;

import timeCat.domain.*;
import timeCat.exceptions.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class TimeCatApp {
    //Repository for all saved project
    private final ArrayList<Project> projectsRepo;

    //Repository for employees
    private final ArrayList<Employee> employeeRepo;

    //Counters to make sure projects and activities have unique ids
    private int activityCount;
    private int projectCount;

    private Employee loggedInUser;

    public TimeCatApp(){
        projectsRepo = new ArrayList<>();
        employeeRepo = new ArrayList<>();
        employeeRepo.add(new Employee("ADM"));
    }


    ////Login functionality
    //@author  Benjamin Fríðberg - s224347
    public Employee getLoggedInUser(){
        return loggedInUser;
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean IsEmployeeLoggedIn(){
        // assert loggedInUser != null: "No employee is logged in";
        return loggedInUser != null;
    }

    //@author  Benjamin Fríðberg - s224347
    public void login(String initials) throws NotAllowedException, NotFoundException {
        if(IsEmployeeLoggedIn()){
            throw new NotAllowedException("A employee is already logged in");
        }
        loggedInUser = getEmployee(initials);
    }

    //@author  Benjamin Fríðberg - s224347
    public void logout() throws NotAllowedException {
        if(!IsEmployeeLoggedIn()){
            throw new NotAllowedException("Can't logout since no user is logged in");
        }
        loggedInUser = null;
    }

    //////Permission levels
    //@author  Benjamin Fríðberg - s224347
    public void validateEmployeePermissions() throws NotAllowedException {
        if (!IsEmployeeLoggedIn()){
            throw new NotAllowedException("You need to be logged in to perform this action");
        }
    }

    //@author  Benjamin Fríðberg - s224347
    public void validatePMPermissions(String projectID) throws NotAllowedException, NotFoundException {
        validateEmployeePermissions();
        Employee ProjectManager = getProjectByID(projectID).getPM();
        if(ProjectManager != loggedInUser){
            throw new NotAllowedException("You need to be a project manager for this project to perform this action");
        }
    }

    ////Projects functionality
    //@author  Benjamin Fríðberg - s224347
    //DBC - Christian Colberg - s224343
    public boolean hasProject(String projectName){

        // Design by Contract
        // Preconditions
        // assert projectName != null && !projectName.isEmpty(): "Project name should be a non-empty string";

        return projectsRepo.stream().anyMatch(p -> p.getName().equals(projectName));

    }

    //@author  Benjamin Fríðberg - s224347
    public String getNextProjectID(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        return currentYear+String.format("%04d",projectCount+1);
    }

    public ArrayList<Project> getProjects() {
        return projectsRepo;
    }

    //@author Benjamin Fríðberg - s224347
    public Project getProjectByID(String projectID) throws NotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getID().equals(projectID)).findFirst();
        if (FoundProject.isPresent()){
            return FoundProject.get();
        }
        throw new NotFoundException("The project is not found");
    }

    //@author  Benjamin Fríðberg - s224347
    public Project createCostumerProject(String projectName) throws InvalidNameException, DuplicateException, NotAllowedException {
        validateEmployeePermissions();
        if(hasProject(projectName)) {
            throw new DuplicateException("Project with the same name already exists");
        }
        Project costumerProject = new CostumerProject(projectName, getNextProjectID());
        projectsRepo.add(costumerProject);
        projectCount++;
        return costumerProject;
    }

    //@author  Benjamin Fríðberg - s224347
    public Project createInternalProject(String projectName) throws InvalidNameException, DuplicateException, NotAllowedException {
        validateEmployeePermissions();
        if(hasProject(projectName)) {
            throw new DuplicateException("Project with the same name already exists");
        }
        Project internalProject = new InternalProject(projectName, getNextProjectID());
        projectsRepo.add(internalProject);
        projectCount++;
        return internalProject;
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeProject(String projectID) throws NotFoundException, NotAllowedException {
        validatePMPermissions(projectID);
        projectsRepo.remove(getProjectByID(projectID));
    }


    ////Activity functionality
    //@author  Benjamin Fríðberg - s224347
    public ArrayList<Activity> getProjectActivities(String projectID) throws NotFoundException {
        return getProjectByID(projectID).getActivities();
    }

    //@author: Christian Colberg - s224343
    public String getNextActivityID(){
        return "A"+(activityCount+1);
    }

    //@author: Christian Colberg - s224343
    public Activity createActivity(String activityName, String projectID) throws InvalidNameException, NotFoundException, DuplicateException, NotAllowedException {
        validatePMPermissions(projectID);
        Project project = getProjectByID(projectID);
        String newActivityID = getNextActivityID();
        Activity newActivity = new Activity(activityName,newActivityID);
        project.addActivity(newActivity);
        activityCount++;
        return newActivity;
    }

    //@author: Christian Colberg - s224343
    public void removeActivity(String activityID, String projectID) throws NotFoundException, NotAllowedException {
        validatePMPermissions(projectID);
        getProjectByID(projectID).removeActivity(activityID);
    }


    ////Employee functionality
    //@author  Benjamin Fríðberg - s224347
    //DBC - Christian Colberg - s224343
    public Employee registerEmployee(String initials) throws DuplicateException, InvalidNameException, NotAllowedException {
        validateEmployeePermissions();

    // Design by Contract
    // Preconditions
    // assert IsEmployeeLoggedIn():"No employee is logged in";
    // assert initials.length > 0 && initials.length >= 4: "Initials doesn't meet conditions";
    // assert !hasEmployee(initials): "Duplicate name";

        if (initials.length() == 0 || initials.length() > 4){
            throw new InvalidNameException("Invalid initials length");
        }
        if(hasEmployee(initials)){
            throw new DuplicateException("The user could not be registered");
        }
        Employee employee = new Employee(initials);
        employeeRepo.add(employee);
        return employee;

    // Postconditions
    // assert employeeRepo.contains(employee):"The employee was not registered successfully";
}


    //@author  Benjamin Fríðberg - s224347
    public void unregisterEmployee(String initials) throws NotFoundException, NotAllowedException {
        validateEmployeePermissions();
        employeeRepo.remove(getEmployee(initials));
    }

    //@author  Benjamin Fríðberg - s224347 & Christian Colberg - s224343
    //DBC - Christian Colberg - s224343
    public Employee getEmployee(String initials) throws NotFoundException {

// Design by Contract
        // Preconditions
        // assert initials != null && !initials.isEmpty(): "Initials should not be an empty string";
        // assert !employeeRepo.isEmpty(): "Employee repository is empty";
        // assert hasEmployee(initials): "No match found in repository";

        Optional<Employee> employeeSearch = employeeRepo.stream().filter(p -> p.getInitials().equalsIgnoreCase(initials)).findFirst();
        if (employeeSearch.isPresent()){
            return employeeSearch.get();
        }
        throw new NotFoundException("The employee is not found");

        // Postconditions
        // assert employeeSearch≠null: "Something went wrong, employee not found";
    }

    //@author  Benjamin Fríðberg - s224347
    public ArrayList<Employee> getEmployees() {
        return employeeRepo;
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean hasEmployee(String initials){
        return employeeRepo.stream().anyMatch(p -> p.getInitials().equalsIgnoreCase(initials));
    }


    ////Project Manager functionality
    //@author  Benjamin Fríðberg - s224347
    public void assignPM(String projectID, String initials) throws NotFoundException, NotAllowedException {
        validateEmployeePermissions();
        Project project = getProjectByID(projectID);
        if(project.getPM() != null){
            throw new NotAllowedException("Project manager for this project is already assigned");
        }
        project.assignPM(getEmployee(initials));
    }

    //author: Lukas Halberg - s216229
    public void deassignPM(String projectID) throws NotAllowedException, NotFoundException {
        validatePMPermissions(projectID);
        Project project = getProjectByID(projectID);
        project.deassignPM();
    }

    //author: Lukas Halberg - s216229
    public void registerTime(String projectID, String activityID, Calendar date, double hoursSpent) throws NotFoundException, NotAllowedException, IllegalArgumentException {
        validateEmployeePermissions();
        Project project = getProjectByID(projectID);
        Activity activity = project.getActivityByID(activityID);
        Timesheet timesheet = activity.getTimesheet();
        timesheet.add(date, loggedInUser, hoursSpent);

    }

    //@author  Benjamin Fríðberg - s224347
    public ArrayList<ReportEntry> getTodayTimeReport() throws NotAllowedException {
        validateEmployeePermissions();
        ArrayList<ReportEntry> timeReport = new ArrayList<>();
        for(Project project : projectsRepo){
           for(Activity activity : project.getActivities()){
               for(TimesheetEntry timesheetEntry : activity.getTimesheet().getEntries()){
                   Calendar today = Calendar.getInstance();
                   if(timesheetEntry.getEmployee() == loggedInUser && isSameDate(timesheetEntry.getDate(),today)){
                       timeReport.add(new ReportEntry(project,activity,timesheetEntry.getDate(),timesheetEntry.getEmployee(),timesheetEntry.getHours()));
                   }
               }
           }
        }
       return timeReport;
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean isSameDate(Calendar cal1, Calendar cal2){
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
}
