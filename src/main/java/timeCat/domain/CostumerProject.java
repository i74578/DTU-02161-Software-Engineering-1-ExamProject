package timeCat.domain;

import timeCat.exceptions.InvalidNameException;

import java.util.Objects;

//@author  Benjamin Fríðberg - s224347
public class CostumerProject extends Project{
    public CostumerProject(String name, String projectNumber) throws InvalidNameException {
        super(name, projectNumber);
    }

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        String pmInitials = "NaN PM";
        if(super.getPM() != null){
            pmInitials = super.getPM().getInitials();
        }
        return new String[] {super.getID(), super.getName(), "Customer Project", pmInitials};
    }
}
