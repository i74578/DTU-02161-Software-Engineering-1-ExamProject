package cucumber;
import io.cucumber.java.en_old.Ac;
import org.junit.*;
import timeCat.application.TimeCatApp;
import timeCat.domain.Activity;
import timeCat.domain.Employee;
import timeCat.domain.*;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;
import java.util.function.BooleanSupplier;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//@author Emil SB Sundberg
public class Whiteboxtest4 {
    @Test (expected = java.lang.NullPointerException.class)
    public void addActivityNoData() throws InvalidNameException, NotAllowedException, DuplicateException, NotFoundException {
        Project project = new Project("asd","230001");
        project.addActivity(null);

    }
    @Test
    public void addActivitySucess() throws InvalidNameException, NotAllowedException, DuplicateException, NotFoundException {
        Project project = new Project("asd","230001");
        project.addActivity(new Activity("asd","A1"));
        assertTrue(project.hasActivity("asd"));
    }

    @Test (expected = DuplicateException.class)
    public void addActivityDoublet() throws NotAllowedException, NotFoundException, InvalidNameException, DuplicateException {
        Project project = new Project("asd","230001");
        project.addActivity(new Activity("asd","A1"));
        project.addActivity(new Activity("asd","A1"));
    }
}
