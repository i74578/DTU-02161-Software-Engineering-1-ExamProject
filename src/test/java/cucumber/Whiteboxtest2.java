package cucumber;
import org.junit.*;
import timeCat.application.TimeCatApp;
import timeCat.exceptions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
//@author Emil SB Sundberg
public class Whiteboxtest2 {

    @Test (expected = NotFoundException.class)
    public void getemployeeWrongFormat() throws NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        String testInitials = "EMSBS";
        timeCatApp.getEmployee(testInitials);
    }

    @Test
    public  void getEmployeeSucess() throws NotAllowedException, NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        String testInitials = "ESBS";
        timeCatApp.login("USR");
        try {
            timeCatApp.registerEmployee(testInitials);
        } catch (Exception ignored) {}
        assertTrue(timeCatApp.hasEmployee(testInitials));
    }

    @Test (expected = NotFoundException.class)
    public void getemployeeNoData() throws NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        String testvar3 = "";
        timeCatApp.getEmployee(testvar3);
    }
}