package timeCat.app;

import timeCat.domain.Employee;
import timeCat.domain.Project;
import java.util.ArrayList;
import java.util.Optional;

//@author  Benjamin Fríðberg - s224347
public class timeCatApp {
    ArrayList<Project> projectsRepo = new ArrayList<>();
    ArrayList<Employee> employeeRepo = new ArrayList<>();
    Employee loggedInUser;

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



}
