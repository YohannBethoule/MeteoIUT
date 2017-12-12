package metier;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;

import static java.lang.Math.abs;

public class ProgressDisplay extends DisplaySensor {
    static final double ZERO_PROGB = 0.5;

    private DoubleProperty progressTemperature=new SimpleDoubleProperty();

    public ProgressDisplay(ISensor sensor) {
        super(sensor);
    }

    public double getProgressTemperature(){ return progressTemperature.get(); }
    public void setProgressTemperature(double temp) {
        if(displayedSensor.getTemperature()<0){
            temp=ZERO_PROGB-abs(displayedSensor.getTemperature()/100);
        }
        temp= (displayedSensor.getTemperature()/100.0)+ZERO_PROGB;
        if(temp >=1){temp=1;}
        this.progressTemperature.set(temp);
    }
    public DoubleProperty progressTemperatureProperty(){
        return progressTemperature;
    }

    @Override
    public ObjectProperty display() {
        displayedSensor.update();
        setProgressTemperature(displayedSensor.getTemperature());
        return progressTemperatureProperty().asObject();
    }
}
