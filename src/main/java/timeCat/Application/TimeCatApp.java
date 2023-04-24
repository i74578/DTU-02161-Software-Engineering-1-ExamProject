package timeCat.Application;

import timeCat.Domain.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class TimeCatApp {
    private static ArrayList<Project> projectsRepo = new ArrayList<>();
    private static ArrayList<Employee> employeeRepo = new ArrayList<>();
    private static int activityCount = 0;

    public ArrayList<Project> getProjects(){
        return projectsRepo;
    }

    //@author Benjamin Fríðberg - s224347
    public Project getProjectByID(String projectID) throws ProjectNotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getID().equals(projectID)).findFirst();
        if (!FoundProject.isEmpty()){
            return FoundProject.get();
        }
        throw new ProjectNotFoundException("The project is not found");
    }

    //@author Benjamin Fríðberg - s224347
    public Project getProjectByName(String projectName) throws ProjectNotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getName().equals(projectName)).findFirst();
        if (!FoundProject.isEmpty()){
            return FoundProject.get();
        }
        throw new ProjectNotFoundException("The project is not found");
    }

    //@author  Benjamin Fríðberg - s224347
    public void removeProject(String projectID) throws ProjectNotFoundException {
        Project projectToRemove = getProjectByID(projectID);
        projectsRepo.remove(projectToRemove);
    }

    //@author  Benjamin Fríðberg - s224347
    public void createCostumerProject(String projectName) throws InvalidProjectNameException, DuplicateException {
        if(!projectExists(projectName)) {
            Project costumerProject = new CostumerProject(projectName, getNextProjectID());
            projectsRepo.add(costumerProject);
            return;
        }
        throw new DuplicateException("Project with the same name already exists");
    }

    //@author  Benjamin Fríðberg - s224347
    public void createInternalProject(String projectName) throws InvalidProjectNameException {
        Project internalProject = new InternalProject(projectName,getNextProjectID());
        projectsRepo.add(internalProject);
    }

    //@author  Benjamin Fríðberg - s224347
    public static String getNextProjectID(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        return currentYear+String.format("%04d",projectsRepo.size()+1);
    }

    //author: Christian Colberg - s224343
    public static int getNextActivityID(){
        return activityCount+1;
    }



    //author: Christian Colberg - s224343
    public void createActivity(String activityName, String projectID) throws InvalidActivityNameException, ProjectNotFoundException {
        Project project = getProjectByID(projectID);
        Activity activityToAdd = new Activity(activityName,getNextActivityID());
        project.addActivity(activityToAdd);
        activityCount++;
    }

    //@author  Benjamin Fríðberg - s224347
    public void addEmployee(String initials) throws DuplicateException {
        if(!userExists(initials)){
            Employee employee = new Employee(initials);
            employeeRepo.add(employee);
            return;
        }
        throw new DuplicateException("The user already exists");
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean projectExists(String projectName){
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getName().equals(projectName)).findFirst();
        return !FoundProject.isEmpty();
    }

    //@author  Benjamin Fríðberg - s224347
    public boolean userExists(String initials){
        Optional<Employee> FoundEmployee = employeeRepo.stream().filter(p -> p.getInitials().equals(initials)).findFirst();
        return !FoundEmployee.isEmpty();
    }

    //@author  Benjamin Fríðberg - s224347
    public Employee getEmployee(String initials) throws UserNotFoundException {
        Optional<Employee> FoundEmployee = employeeRepo.stream().filter(p -> p.getInitials().equals(initials)).findFirst();
        if (!FoundEmployee.isEmpty()){
            return FoundEmployee.get();
        }
        throw new UserNotFoundException("The user is not found");
    }

    public void removeEmployee(String initials) throws UserNotFoundException {
        Employee employeeToRemove = getEmployee(initials);
        employeeRepo.remove(employeeToRemove);
    }
}
