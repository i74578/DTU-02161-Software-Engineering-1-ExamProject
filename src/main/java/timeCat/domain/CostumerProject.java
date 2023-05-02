package timeCat.domain;

import timeCat.exceptions.InvalidNameException;

//@author  Benjamin Fríðberg - s224347
public class CostumerProject extends Project{
    public CostumerProject(String name, String projectNumber) throws InvalidNameException {
        super(name, projectNumber);
    }

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        return new String[] {super.getID(), super.getName(), "Customer Project"};
    }
}
