package timeCat.domain;

//@author  Benjamin Fríðberg - s224347
public class Employee implements Tabelify {
    private final String initials;


    public Employee(String initials){
        this.initials = initials.toUpperCase();
    }

    public String getInitials(){
        return initials;
    }

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        return new String[] {initials};
    }
}
