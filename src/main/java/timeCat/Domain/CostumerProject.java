package timeCat.Domain;

import timeCat.Application.InvalidProjectNameException;

public class CostumerProject extends Project{
    public CostumerProject(String name, String projectNumber) throws InvalidProjectNameException {
        super(name, projectNumber);
    }
}
