package metier;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import metier.Sensor.ISensor;

public abstract class DisplaySensor{
    protected ISensor displayedSensor;
    protected StringProperty name=new SimpleStringProperty();


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
