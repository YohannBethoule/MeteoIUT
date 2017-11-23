package metier;

import javafx.beans.property.*;
import javafx.concurrent.Task;

public class SimpleSensor implements ISensor {
    private  int refreshRate;

    private StringProperty name=new SimpleStringProperty();
    private DoubleProperty temperature=new SimpleDoubleProperty();
    private ITemperatureGenerator tempGenerator;


    public SimpleSensor(String name, int refreshRate){
        this.name.set(name);
        this.refreshRate=refreshRate;
        this.tempGenerator=new IntervalGeneration();
        update();
    }


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
