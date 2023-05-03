package timeCat.presentation;
import timeCat.domain.Tabelify;

//@author  Benjamin Fríðberg - s224347
public class feature implements Tabelify {
    private final String featureName;
    private final String featureDescription;
    private final command featureCallMethod;

    //@author  Benjamin Fríðberg - s224347
    public feature(String featureName, String featureDescription, command featureCallMethod){
        this.featureName = featureName;
        this.featureDescription = featureDescription;
        this.featureCallMethod = featureCallMethod;
    }

    public command getFeatureCallMethod(){return featureCallMethod;}

    //@author  Benjamin Fríðberg - s224347
    public String[] getPropertiesForTable(){
        return new String[] {featureName,featureDescription};
    }
}
