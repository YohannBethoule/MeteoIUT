package metier.Sensor;

import javafx.application.Platform;
import javafx.beans.property.*;
import metier.Generation.ITemperatureGenerator;
import metier.Generation.IntervalGeneration;

import static java.lang.Math.abs;

public class SimpleSensor implements ISensor {

    private StringProperty name=new SimpleStringProperty();
    private DoubleProperty temperature=new SimpleDoubleProperty();

    private ITemperatureGenerator tempGenerator;

    public SimpleSensor(String name){
        this.name.set(name);
        this.tempGenerator=new IntervalGeneration();
        update();
    }

    public String getName() { return name.get(); }
    public double getTemperature(){ return temperature.get(); }

    public void setTemperature(double temperature) {
        Platform.runLater(()->{
            this.temperature.set(temperature);
        });
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
    public DoubleProperty temperatureProperty(){ return temperature; }

    @Override
    public void update(){
        double t = tempGenerator.generateTemperature();
        setTemperature(t);
    }

}
