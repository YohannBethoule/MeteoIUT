package metier;

import javafx.beans.property.*;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

public class SimpleSensor implements ISensor {

    private StringProperty name=new SimpleStringProperty();
    private DoubleProperty temperature=new SimpleDoubleProperty();
    private ITemperatureGenerator tempGenerator;


    public SimpleSensor(String name){
        this.name.set(name);
        this.tempGenerator=new RandomGeneration();
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
        setTemperature(tempGenerator.getRandomTemperature());
    }
}
