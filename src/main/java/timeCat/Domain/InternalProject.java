package timeCat.Domain;

import timeCat.Application.InvalidProjectNameException;

public class InternalProject extends Project{
    public InternalProject(String name, String projectNumber) throws InvalidProjectNameException {
        super(name, projectNumber);
    }
}
