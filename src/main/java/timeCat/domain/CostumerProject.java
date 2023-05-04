package timeCat.domain;

import timeCat.exceptions.InvalidNameException;

import java.util.Objects;

//@author  Benjamin Fríðberg - s224347
public class CostumerProject extends Project{
    public CostumerProject(String name, String projectNumber) throws InvalidNameException {
        super(name, projectNumber);
    }
}