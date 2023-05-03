package cucumber;
import timeCat.application.TimeCatApp;
import timeCat.domain.Employee;
import timeCat.domain.Project;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

//@author  Benjamin Fríðberg - s224347
public class ProjectHelper {
    private TimeCatApp timeCatApp;
    private Project project;
    //@author  Benjamin Fríðberg - s224347
    public ProjectHelper(TimeCatApp timeCatApp) {
        this.timeCatApp = timeCatApp;
    }
    //@author  Benjamin Fríðberg - s224347
    public Project createCostumerProject(String projectName) throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        Project project = timeCatApp.createCostumerProject(projectName);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
        return project;
    }
    //@author  Benjamin Fríðberg - s224347
    public Project createInternalProject(String projectName) throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        Project project = timeCatApp.createInternalProject(projectName);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
        return project;
    }
    //@author  Benjamin Fríðberg - s224347
    public void removeProject(String projectID, String PMinitials) throws NotAllowedException, NotFoundException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login(PMinitials);
        }
        timeCatApp.removeProject(projectID);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }
    //@author  Benjamin Fríðberg - s224347
    public void addTestProject() throws Exception{
        project = createCostumerProject(getProject().getName());
    }
    //@author  Benjamin Fríðberg - s224347
    public Project getProject() throws InvalidNameException {
        if(project == null){
            project = getTestProject();
        }
        return project;
    }
    //@author  Benjamin Fríðberg - s224347
    private Project getTestProject() throws InvalidNameException {
        Project testProject = new Project("Test Project","239999");
        return testProject;
    }
    //@author  Benjamin Fríðberg - s224347
    public void setProject(Project project){
        this.project = project;
    }

    //@author  Benjamin Fríðberg - s224347
    public void assignPM(String projectID, String initials) throws NotAllowedException, NotFoundException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        timeCatApp.assignPM(projectID,initials);
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }
    //@author  Benjamin Fríðberg - s224347
    public void assignTestPM() throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Employee loggedInEmployee = timeCatApp.getLoggedInUser();
        if(loggedInEmployee == null){
            timeCatApp.login("ADM");
        }
        timeCatApp.registerEmployee("MGR");
        timeCatApp.assignPM(project.getID(),"MGR");
        if(loggedInEmployee == null){
            timeCatApp.logout();
        }
    }
}
