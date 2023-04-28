package timeCat.Application;

import timeCat.Domain.*;
import timeCat.Exceptions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class TimeCatApp {
    private ArrayList<Project> projectsRepo;
    private ArrayList<Employee> employeeRepo;
    private int activityCount;
    private Employee loggedInUser;

    public TimeCatApp(){
        projectsRepo = new ArrayList<>();
        employeeRepo = new ArrayList<Employee>();
        employeeRepo.add(new Employee("ADM"));
        activityCount = 0;
        Employee loggedInUser = null;
    }


    //////Login

    //@author  Benjamin Fríðberg - s224347
    public Employee GetLoggedInUser(){
        return loggedInUser;
    }

    //@author  Benjamin Fríðberg - s224347
    public void login(String initials) throws NotAllowedException, NotFoundException {
        if(loggedInUser == null){
            loggedInUser = getEmployee(initials);
            return;
        }
        throw new NotAllowedException("A user is already logged in");
    }

    //@author  Benjamin Fríðberg - s224347
    public void logout() throws NotAllowedException {
        if(loggedInUser == null){
            throw new NotAllowedException("Can't logout since no user is logged in");
        }
        loggedInUser = null;
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean IsEmployeeLoggedIn(){
        return loggedInUser != null;
    }



    ///////Projects
    //@author  Benjamin Fríðberg - s224347
    public void createCostumerProject(String projectName) throws InvalidNameException, DuplicateException {
        if(!hasProject(projectName)) {
            Project costumerProject = new CostumerProject(projectName, getNextProjectID());
            projectsRepo.add(costumerProject);
            return;
        }
        throw new DuplicateException("Project with the same name already exists");
    }

    //@author  Benjamin Fríðberg - s224347
    public void createInternalProject(String projectName) throws InvalidNameException, DuplicateException {
        if(!hasProject(projectName)) {
            Project internalProject = new InternalProject(projectName, getNextProjectID());
            projectsRepo.add(internalProject);
            return;
        }
        throw new DuplicateException("Project with the same name already exists");
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeProject(String projectID) throws NotFoundException {
        Project projectToRemove = getProjectByID(projectID);
        projectsRepo.remove(projectToRemove);
    }


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
        if (!FoundProject.isEmpty()){
            return FoundProject.get();
        }
        throw new NotFoundException("The project is not found");
    }

    //@author Benjamin Fríðberg - s224347
    public Project getProjectByName(String projectName) throws NotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getName().equals(projectName)).findFirst();
        if (!FoundProject.isEmpty()){
            return FoundProject.get();
        }
        throw new NotFoundException("The project is not found");
    }



    ///////Activities

    //author: Christian Colberg - s224343
    public void createActivity(String activityName, String projectID) throws InvalidNameException, NotFoundException, DuplicateException {
        Project project = getProjectByID(projectID);
        Activity activityToAdd = new Activity(activityName,getNextActivityID());
        project.addActivity(activityToAdd);
        activityCount++;
    }

    //author: Christian Colberg - s224343
    public void removeActivity(String activityID, String projectID) throws NotFoundException, NotFoundException {
        Project project = getProjectByID(projectID);
        project.removeActivity(activityID);
    }

    //@author  Benjamin Fríðberg - s224347
    public ArrayList<Activity> getProjectActivities(String projectID) throws NotFoundException {
        Project project = getProjectByID(projectID);
        return project.getActivities();
    }

    //author: Christian Colberg - s224343
    public String getNextActivityID(){
        return "A"+String.valueOf(activityCount+1);
    }






    ////////Employee

    //@author  Benjamin Fríðberg - s224347
    public void registerEmployee(String initials) throws DuplicateException, InvalidNameException {
        if(initials.length() == 0 || initials.length() > 4){
            throw new InvalidNameException("Invalid initials");
        }
        if(hasEmployee(initials)){
            throw new DuplicateException("The user could not be registered");
        }
        employeeRepo.add(new Employee(initials));
    }

    //@author  Benjamin Fríðberg - s224347
    public void unregisterEmployee(String initials) throws NotFoundException {
        Employee employeeToRemove = getEmployee(initials);
        employeeRepo.remove(employeeToRemove);
    }

    //@author  Benjamin Fríðberg - s224347
    public Employee getEmployee(String initials) throws NotFoundException {
        Optional<Employee> FoundEmployee = employeeRepo.stream().filter(p -> p.getInitials().equals(initials)).findFirst();
        if (!FoundEmployee.isEmpty()){
            return FoundEmployee.get();
        }
        throw new NotFoundException("The user is not found");
    }

    //@author  Benjamin Fríðberg - s224347
    public ArrayList<Employee> getEmployees() {
        return employeeRepo;
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean hasEmployee(String initials){
        return employeeRepo.stream().anyMatch(p -> p.getInitials().equals(initials));
    }


}
