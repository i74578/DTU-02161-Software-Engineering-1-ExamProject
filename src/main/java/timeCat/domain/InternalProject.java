package timeCat.domain;

import timeCat.exceptions.InvalidNameException;

import java.util.Objects;

//@author  Benjamin Fríðberg - s224347
public class InternalProject extends Project{
    public InternalProject(String name, String projectNumber) throws InvalidNameException {
        super(name, projectNumber);
    }

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        String employeeInitials = "NaN PM";
        if(super.getPM() != null){
            employeeInitials = super.getPM().getInitials();
        }
        return new String[] {super.getID(), super.getName(), "Internal Project", employeeInitials};
    }
}
