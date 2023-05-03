package timeCat.application;

import timeCat.domain.*;
import timeCat.exceptions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class TimeCatApp {
    private final ArrayList<Project> projectsRepo;
    private final ArrayList<Employee> employeeRepo;
    private int activityCount;
    private Employee loggedInUser;

    public TimeCatApp(){
        projectsRepo = new ArrayList<>();
        employeeRepo = new ArrayList<>();
        employeeRepo.add(new Employee("ADM"));
    }


    //////Login
    //@author  Benjamin Fríðberg - s224347
    public Employee getLoggedInUser(){
        return loggedInUser;
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean IsEmployeeLoggedIn(){
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

    ///////Projects
    //@author  Benjamin Fríðberg - s224347
    public boolean hasProject(String projectName){
        return projectsRepo.stream().anyMatch(p -> p.getName().equals(projectName));
    }

    //@author  Benjamin Fríðberg - s224347
    public String getNextProjectID(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        return currentYear+String.format("%04d",projectsRepo.size()+1);
    }

    public ArrayList<Project> getProjects(){
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

    //@author Benjamin Fríðberg - s224347
    public Project getProjectByName(String projectName) throws NotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getName().equals(projectName)).findFirst();
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
        return internalProject;
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeProject(String projectID) throws NotFoundException, NotAllowedException {
        validatePMPermissions(projectID);
        projectsRepo.remove(getProjectByID(projectID));
    }


    ///////Activities
    //@author  Benjamin Fríðberg - s224347
    public ArrayList<Activity> getProjectActivities(String projectID) throws NotFoundException {
        return getProjectByID(projectID).getActivities();
    }

    //author: Christian Colberg - s224343
    public String getNextActivityID(){
        return "A"+(activityCount+1);
    }

    //author: Christian Colberg - s224343
    public Activity createActivity(String activityName, String projectID) throws InvalidNameException, NotFoundException, DuplicateException, NotAllowedException {
        validatePMPermissions(projectID);
        Project project = getProjectByID(projectID);
        String newActivityID = getNextActivityID();
        Activity newActivity = new Activity(activityName,newActivityID);
        project.addActivity(newActivity);
        activityCount++;
        return newActivity;
    }

    //author: Christian Colberg - s224343
    public void removeActivity(String activityID, String projectID) throws NotFoundException, NotAllowedException {
        validatePMPermissions(projectID);
        getProjectByID(projectID).removeActivity(activityID);
    }


    ////////Employee
    //@author  Benjamin Fríðberg - s224347
    public void registerEmployee(String initials) throws DuplicateException, InvalidNameException, NotAllowedException {
        validateEmployeePermissions();
        if(initials.length() == 0 || initials.length() > 4){
            throw new InvalidNameException("Invalid initials");
        }
        if(hasEmployee(initials)){
            throw new DuplicateException("The user could not be registered");
        }
        employeeRepo.add(new Employee(initials));
    }

    //@author  Benjamin Fríðberg - s224347
    public void unregisterEmployee(String initials) throws NotFoundException, NotAllowedException {
        validateEmployeePermissions();
        employeeRepo.remove(getEmployee(initials));
    }

    //@author  Benjamin Fríðberg - s224347
    public Employee getEmployee(String initials) throws NotFoundException {
        Optional<Employee> employeeSearch = employeeRepo.stream().filter(p -> p.getInitials().equals(initials)).findFirst();
        if (employeeSearch.isPresent()){
            return employeeSearch.get();
        }
        throw new NotFoundException("The employee is not found");
    }

    //@author  Benjamin Fríðberg - s224347
    public ArrayList<Employee> getEmployees() {
        return employeeRepo;
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean hasEmployee(String initials){
        return employeeRepo.stream().anyMatch(p -> p.getInitials().equals(initials));
    }


    //Project manager assign and deassign
    //@author  Benjamin Fríðberg - s224347
    public void assignPM(String projectID, String initials) throws NotFoundException, NotAllowedException {
        validateEmployeePermissions();
        Project project = getProjectByID(projectID);
        if(project.getPM() != null){
            throw new NotAllowedException("Project manager for this project is already assigned");
        }
        project.assignPM(getEmployee(initials));
    }

    public void deassignPM(String projectID) throws NotAllowedException, NotFoundException {
        validatePMPermissions(projectID);
        Project project = getProjectByID(projectID);
        if(project.getPM() == null){
            throw new NotAllowedException("Can't deassign PM, when not assigned");
        }
        project.deassignPM();
    }
}
