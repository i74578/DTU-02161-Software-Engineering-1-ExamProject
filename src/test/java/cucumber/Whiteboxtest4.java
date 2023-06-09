package cucumber;

import org.junit.*;
import timeCat.domain.Activity;

import timeCat.domain.Project;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import static org.junit.jupiter.api.Assertions.assertTrue;
//@author Emil SB Sundberg
public class Whiteboxtest4 {
    @Test (expected = java.lang.NullPointerException.class)
    public void addActivityNoData() throws InvalidNameException, DuplicateException {
        Project project = new Project("Project Name","230001");
        project.addActivity(null);
    }

    @Test
    public void addActivitySucess() throws InvalidNameException, DuplicateException {
        Project project = new Project("Project Name","230001");
        project.addActivity(new Activity("Activity Name","A1"));
        assertTrue(project.hasActivity("Activity Name"));
    }

    @Test (expected = DuplicateException.class)
    public void addActivityDoublet() throws InvalidNameException, DuplicateException {
        Project project = new Project("Project Name","230001");
        project.addActivity(new Activity("Activity Name","A1"));
        project.addActivity(new Activity("Activity Name","A1"));
    }
}
