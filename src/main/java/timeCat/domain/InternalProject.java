package timeCat.domain;

import timeCat.exceptions.InvalidNameException;

//@author  Benjamin Fríðberg - s224347
public class InternalProject extends Project{
    public InternalProject(String name, String projectNumber) throws InvalidNameException {
        super(name, projectNumber);
    }
}
