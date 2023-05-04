package timeCat.domain;

//@author  Benjamin Fríðberg - s224347
public class Employee{
    private final String initials;


    public Employee(String initials){
        this.initials = initials.toUpperCase();
    }

    public String getInitials(){
        return initials;
    }
}
