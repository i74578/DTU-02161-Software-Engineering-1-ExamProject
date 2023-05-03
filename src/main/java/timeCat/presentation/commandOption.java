package timeCat.presentation;
import timeCat.domain.Tabelify;

//@author  Benjamin Fríðberg - s224347
public class commandOption implements Tabelify {
    private final String optionName;
    private final String optionDescription;
    private final command optionCallMethod;

    //@author  Benjamin Fríðberg - s224347
    public commandOption(String optionName,String optionDescription, command optionCallMethod){
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.optionCallMethod = optionCallMethod;
    }

    public command getOptionCallMethod(){return optionCallMethod;}

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        return new String[] {optionName,optionDescription};
    }
}
