package timeCat.domain;

import timeCat.app.InvalidProjectNameException;

public class InternalProject extends Project{
    public InternalProject(String name, String projectNumber) throws InvalidProjectNameException {
        super(name, projectNumber);
    }
}
