package timeCat.Domain;

//@author  Benjamin Fríðberg - s224347
public class Employee {
    private String initials;

    //@author  Benjamin Fríðberg - s224347
    public Employee(String initials){
        this.initials = initials;
    }

    public String getInitials(){
        return initials;
    }
}
