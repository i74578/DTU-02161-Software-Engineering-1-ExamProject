package timeCat.domain;

import timeCat.app.InvalidProjectNameException;

public class CostumerProject extends Project{
    public CostumerProject(String name, String projectNumber) throws InvalidProjectNameException {
        super(name, projectNumber);
    }
}
