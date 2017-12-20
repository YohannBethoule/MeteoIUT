package metier.sensor;

import javafx.application.Platform;
import javafx.beans.property.*;
import metier.generation.ITemperatureGenerator;
import metier.generation.IntervalGeneration;

import static java.lang.Math.abs;

public class SimpleSensor extends ISensor {

    public SimpleSensor(String name,ITemperatureGenerator generator){
        super(name,generator);
        update();

    }

    @Override
    public void update(){
        double t = tempGenerator.generateTemperature();
        setTemperature(t);
    }

}
