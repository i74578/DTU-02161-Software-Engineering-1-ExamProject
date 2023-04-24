package timeCat.Presentation;
import timeCat.Domain.Tabelify;

//@author  Benjamin Fríðberg - s224347
public class commandOption implements Tabelify {
    String optionName;
    String optionDescription;
    command optionCallMethod;

    //@author  Benjamin Fríðberg - s224347
    public commandOption(String optionName,String optionDescription, command optionCallMethod){
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.optionCallMethod = optionCallMethod;
    }

    public String getOptionName(){return optionName;}
    public String getOptionDescription(){return optionDescription;}
    public command getOptionCallMethod(){return optionCallMethod;}

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        return new String[] {optionName,optionDescription};
    }



}
