package timeCat.Domain;

import timeCat.Application.InvalidProjectNameException;

//@author  Benjamin Fríðberg - s224347
public class CostumerProject extends Project{
    public CostumerProject(String name, String projectNumber) throws InvalidProjectNameException {
        super(name, projectNumber);
    }
}
