package metier;

import javafx.beans.property.*;
import javafx.concurrent.Task;

import static java.lang.Math.abs;

public class SimpleSensor implements ISensor {
    private  int refreshRate;
    static final double ZERO_PROGB = 0.5;

    private StringProperty name=new SimpleStringProperty();
    private DoubleProperty temperature=new SimpleDoubleProperty();
    private DoubleProperty progressTemperature=new SimpleDoubleProperty();
    private ITemperatureGenerator tempGenerator;


    public SimpleSensor(String name, int refreshRate){
        this.name.set(name);
        this.refreshRate=refreshRate;
        this.tempGenerator=new IntervalGeneration();
        update();
    }


    public DoubleProperty progessTemperatureProperty(){
        setProgressTemperature();
        return progressTemperature;
    }

    public void setProgressTemperature() {
        double temp;
        if(getTemperature()<0){
            temp=ZERO_PROGB-abs(getTemperature()/100);
        }
        temp= (getTemperature()/100.0)+ZERO_PROGB;
        if(temp >=1){temp=1;}
        this.progressTemperature.set(temp);
    }

    public double getProgressTemperature(){ return progressTemperature.get(); }
    public StringProperty nameProperty() {
        return name;
    }
    public String getName() { return name.get(); }
    public void setName(String name) {
        this.name.set(name);
    }

    public DoubleProperty temperatureProperty(){ return temperature; }
    public Double getTemperature(){ return temperature.get(); }
    public void setTemperature(Double temperature) {
        this.temperature.set(temperature);
    }

    public void update(){
        setTemperature(tempGenerator.generateTemperature());
    }

}
