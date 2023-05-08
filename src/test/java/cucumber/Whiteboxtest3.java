package cucumber;
import org.junit.*;
import timeCat.application.TimeCatApp;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
//@author Emil SB Sundberg
public class Whiteboxtest3 {
    @Test
    public void hasProjectNotPresent (){
        TimeCatApp timeCatApp = new TimeCatApp();
         String projectName = "Borfors 1";
         try {
             timeCatApp.hasProject(projectName);
         } catch (Exception ignored){}
         assertFalse(timeCatApp.hasProject(projectName));
    }

    @Test
    public void hasProjectSucess () throws NotAllowedException, NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        String projectName = "DSB 2";
        timeCatApp.login("USR");
        try {
            timeCatApp.createInternalProject(projectName);
        } catch (Exception ignored) {}
        assertTrue(timeCatApp.hasProject(projectName));
    }

    @Test
    public void hasProjectNoData () throws NotAllowedException, NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        String projectName = "";
        timeCatApp.login("USR");
        try {
            timeCatApp.hasProject(projectName);
        } catch (Exception ignored) {}
        assertFalse(timeCatApp.hasProject(projectName));
    }
}
