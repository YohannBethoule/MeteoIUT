package metier;

import javafx.beans.property.ObjectProperty;

public abstract class DisplaySensor {
    protected ISensor displayedSensor;

    public ISensor getISensor(){
        return displayedSensor;
    }

    public void setDisplayedSensor(ISensor displayedSensor) {
        this.displayedSensor = displayedSensor;
    }
    public DisplaySensor(ISensor sensor){
        setDisplayedSensor(sensor);
    }

    public abstract ObjectProperty display();
}
