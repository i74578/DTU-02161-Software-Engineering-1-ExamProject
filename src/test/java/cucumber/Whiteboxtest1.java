package cucumber;

import org.junit.*;
import timeCat.application.TimeCatApp;
import timeCat.exceptions.DuplicateException;
import timeCat.exceptions.InvalidNameException;
import timeCat.exceptions.NotAllowedException;
import timeCat.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;
//@author Emil SB Sundberg

public class Whiteboxtest1 {

   @Test(expected = InvalidNameException.class )
    public void registerEmployeeWrongFormat() throws InvalidNameException, NotAllowedException, DuplicateException, NotFoundException {
        TimeCatApp timeCatApp = new TimeCatApp();
        timeCatApp.login("USR");
        String testInitials = "EMSBS";
        timeCatApp.registerEmployee(testInitials);
    }

  @Test
    public  void registerEmployeeSucess() throws NotAllowedException, NotFoundException {
      TimeCatApp timeCatApp = new TimeCatApp();
        String testInitials = "ESBS";
        timeCatApp.login("USR");
        try {
            timeCatApp.registerEmployee(testInitials);
        } catch (Exception ignored) {}
        assertTrue(timeCatApp.hasEmployee(testInitials));
    }

   @Test (expected = InvalidNameException.class )
    public void registeremployeeNoData() throws InvalidNameException, NotAllowedException, DuplicateException, NotFoundException {
       TimeCatApp timeCatApp = new TimeCatApp();
       timeCatApp.login("USR");
       String testInitials = "";
       timeCatApp.registerEmployee(testInitials);
    }
}