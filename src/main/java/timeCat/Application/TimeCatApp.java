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

    public void valdiateEmployee() throws NotAllowedException {
        if (!IsEmployeeLoggedIn()){
            throw new NotAllowedException("You need to be logged in to perform this action");
        }
    }

    public void valdiatePM(String projectID) throws NotAllowedException, NotFoundException {
        valdiateEmployee();
        Project project = getProjectByID(projectID);
        Employee pm = project.getPM();
        if(pm != loggedInUser){
            throw new NotAllowedException("You need to be a project manager for this project to perform this action");
        }

    }


    ///////Projects
    //@author  Benjamin Fríðberg - s224347
    public String createCostumerProject(String projectName) throws InvalidNameException, DuplicateException, NotAllowedException {
        valdiateEmployee();
        if(!hasProject(projectName)) {
            String projectID = getNextProjectID();
            Project costumerProject = new CostumerProject(projectName, projectID);
            projectsRepo.add(costumerProject);
            return projectID;
        }
        throw new DuplicateException("Project with the same name already exists");
    }

    //@author  Benjamin Fríðberg - s224347
    public String createInternalProject(String projectName) throws InvalidNameException, DuplicateException, NotAllowedException {
        valdiateEmployee();
        if(!hasProject(projectName)) {
            String projectID = getNextProjectID();
            Project internalProject = new InternalProject(projectName, projectID);
            projectsRepo.add(internalProject);
            return projectID;
        }
        throw new DuplicateException("Project with the same name already exists");
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeProject(String projectID) throws NotFoundException, NotAllowedException {
        valdiatePM(projectID);
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
    public Activity createActivity(String activityName, String projectID) throws InvalidNameException, NotFoundException, DuplicateException, NotAllowedException {
        valdiatePM(projectID);
        Project project = getProjectByID(projectID);
        String activityID = getNextActivityID();
        Activity activityToAdd = new Activity(activityName,activityID);
        project.addActivity(activityToAdd);
        activityCount++;
        return activityToAdd;
    }

    //author: Christian Colberg - s224343
    public void removeActivity(String activityID, String projectID) throws NotFoundException, NotFoundException, NotAllowedException {
        valdiatePM(projectID);
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
    public void registerEmployee(String initials) throws DuplicateException, InvalidNameException, NotAllowedException {
        valdiateEmployee();
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
        valdiateEmployee();
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


    public void assignPM(String projectID, String initials) throws NotFoundException, NotAllowedException {
        valdiateEmployee();
        Project project = getProjectByID(projectID);
        if(project.getPM() != null){
            throw new NotAllowedException("Project manager for this project is already assigned");
        }
        Employee employee = getEmployee(initials);
        project.assignPM(employee);
    }

    public void deassignPM(String projectID) throws NotAllowedException, NotFoundException {
        valdiatePM(projectID);
        Project project = getProjectByID(projectID);
        if(project.getPM() == null){
            throw new NotAllowedException("Can't deassign PM, when not assigned");
        }
        project.deassignPM();
    }


}
