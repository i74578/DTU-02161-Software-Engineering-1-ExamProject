package timeCat.Domain;

import timeCat.Application.InvalidProjectNameException;

//@author  Benjamin Fríðberg - s224347
public class InternalProject extends Project{
    public InternalProject(String name, String projectNumber) throws InvalidProjectNameException {
        super(name, projectNumber);
    }

    //@author  Benjamin Fríðberg - s224347
    public String[] getMainProperties(){
        return new String[] {projectID, name, "Internal Project"};
    }
}
