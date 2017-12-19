package metier.sensor;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.HashMap;
import java.util.Map;

public class SuperSensor extends ISensor{
    private Map<ISensor, Integer> sensors;
    private StringProperty name=new SimpleStringProperty();
    private DoubleProperty temperature=new SimpleDoubleProperty();


    public SuperSensor(String name){
        setName(name);
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

    public void addSensor(ISensor s, int coeff){
        sensors.put(s, coeff);
    }

    public void deleteSensor(ISensor s){
        sensors.remove(s);
    }
}
