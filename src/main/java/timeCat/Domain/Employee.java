package timeCat.Domain;

import javax.swing.table.TableCellEditor;

//@author  Benjamin Fríðberg - s224347
public class Employee implements Tabelify {
    private String initials;


    public Employee(String initials){
        this.initials = initials;
    }

    public String getInitials(){
        return initials;
    }

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        return new String[] {initials};
    }
}
