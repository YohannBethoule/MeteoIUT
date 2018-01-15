package metier.sensor;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import metier.generation.ITemperatureGenerator;

import java.util.HashMap;
import java.util.Map;

public class SuperSensor extends ISensor{
    private Map<ISensor, Integer> sensors;

    public SuperSensor(String name){
        super(name);
        sensors=new HashMap<>();
    }


    @Override
    public void update() {
        double total=0;
        int count=0;
        for (Map.Entry<ISensor, Integer> s: sensors.entrySet()) {
            total+=(s.getKey().getTemperature())*s.getValue();
            count+=s.getValue();
        }
        setTemperature(total/count);
    }

    public void addSensor(ISensor s, int weight){
        sensors.put(s, weight);
    }

    public void deleteSensor(ISensor s){
        sensors.remove(s);
    }
}
