package timeCat.app;

import timeCat.domain.Employee;
import timeCat.domain.Project;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class timeCatApp {
    private static ArrayList<Project> projectsRepo = new ArrayList<>();
    private static ArrayList<Employee> employeeRepo = new ArrayList<>();

    public ArrayList<Project> getProjects(){
        return projectsRepo;
    }

    //@author  Benjamin Fríðberg - s224347
    public Project getProject(int projectID) throws ProjectNotFoundException {
        Optional<Project> FoundProject = projectsRepo.stream().filter(p -> p.getID() == projectID).findFirst();
        if (!FoundProject.isEmpty()){
            return FoundProject.get();
        }
        throw new ProjectNotFoundException("The project \"" + projectID + "\" is not found");
    }

    //@author  Benjamin Fríðberg - s224347
    public void createProject(String projectName,boolean customerProject){
        Project createdProject = new Project(projectName,getNextProjectID(),customerProject);
        projectsRepo.add(createdProject);
    }

    //@author  Benjamin Fríðberg - s224347
    public static int getNextProjectID(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) % 100;
        return Integer.parseInt(currentYear+String.format("%06d",projectsRepo.size()+1));
    }



}
