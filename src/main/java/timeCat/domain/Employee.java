package timeCat.domain;

//@author  Benjamin Fríðberg - s224347
public class Employee {
    private String name;
    private String initials;

    //@author  Benjamin Fríðberg - s224347
    public Employee(String name, String initials){
        this.name = name;
        this.initials = initials;
    }

    //@author  Benjamin Fríðberg - s224347
    public String getName(){
        return name;
    }

    //@author  Benjamin Fríðberg - s224347
    public String getInitials(){
        return initials;
    }
}
