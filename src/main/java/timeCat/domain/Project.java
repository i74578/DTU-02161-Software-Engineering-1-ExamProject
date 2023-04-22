package timeCat.domain;

import java.util.Calendar;

//@author  Benjamin Fríðberg - s224347
public class Project {
    String name;
    int projectID;
    Calendar startTimestamp;
    Calendar finishTimestamp;
    boolean customerProject;

    public int getID(){
        return projectID;
    }

    public Project(String name, int projectNumber,boolean customerProject){
            
    }

}
