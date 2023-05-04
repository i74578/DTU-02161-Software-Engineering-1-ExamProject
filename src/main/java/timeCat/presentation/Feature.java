package timeCat.presentation;

//@author  Benjamin Fríðberg - s224347
public class Feature {
    private final String featureName;
    private final String featureDescription;
    private final command featureCallMethod;

    //@author  Benjamin Fríðberg - s224347
    public Feature(String featureName, String featureDescription, command featureCallMethod){
        this.featureName = featureName;
        this.featureDescription = featureDescription;
        this.featureCallMethod = featureCallMethod;
    }

    public command getFeatureCallMethod(){return featureCallMethod;}

    public String getName(){
        return featureName;
    }

    public String getDescription(){
        return featureDescription;
    }
}
