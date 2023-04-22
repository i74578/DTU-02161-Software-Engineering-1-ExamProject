package timeCat.Presentation;

import timeCat.Domain.Tabelify;

public class commandOption implements Tabelify {
    String optionName;
    String optionDescription;
    command optionCallMethod;

    public commandOption(String optionName,String optionDescription, command optionCallMethod){
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.optionCallMethod = optionCallMethod;
    }

    public String getOptionName(){return optionName;}
    public String getOptionDescription(){return optionDescription;}
    public command getOptionCallMethod(){return optionCallMethod;}

    public String[] getMainProperties(){
        return new String[] {optionName,optionDescription};
    }



}
