package metier.Sensor;

import javafx.application.Platform;
import javafx.beans.property.*;
import metier.Generation.ITemperatureGenerator;
import metier.Generation.IntervalGeneration;

import static java.lang.Math.abs;

public class SimpleSensor extends ISensor {

    private DoubleProperty temperature=new SimpleDoubleProperty();

    private ITemperatureGenerator tempGenerator;

    public SimpleSensor(String name){
        this.name.set(name);
        this.tempGenerator=new IntervalGeneration();
        update();
    }

    public void setTemperature(double temperature) {
        Platform.runLater(()->{
            this.temperature.set(temperature);
        });
    }

    @Override
    public void update(){
        double t = tempGenerator.generateTemperature();
        setTemperature(t);
    }

}
