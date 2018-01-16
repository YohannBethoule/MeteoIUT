package metier.serialization;

import javafx.beans.property.*;
import metier.sensor.ISensor;

import java.io.Serializable;

public class SerializableSensor extends ISensor implements Serializable {



    private transient ISensor sensor;


    /**
     * Create a XMLSensor using an ISensor
     *
     * @param model the ISensor to convert in XMLSensor
     */
    public SerializableSensor(ISensor model) {
        super(model.getName());
        this.sensor = model;
        name = new SimpleStringProperty(sensor.getName());
        temp = new SimpleDoubleProperty(sensor.getTemperature());
    }


    /**
     * Get the ISensor who corresponds to the XMLSensor
     *
     * @return the ISensor as model to create the XMLSensor
     */
    public ISensor getSensor() {
        return sensor;
    }

    /**
     * ISensor name Property to be serialized
     */
    private final StringProperty name;
    @Override public StringProperty nameProperty() {return sensor.nameProperty();}
    @Override public  String getName() {return sensor.getName();}
    @Override public void setName(String name) {sensor.setName(name);}

    /**
     * ISensor temperature Property to be serialized
     */
    private final DoubleProperty temp;
    @Override public DoubleProperty temperatureProperty() {return sensor.temperatureProperty();}
    @Override public double getTemperature() {return sensor.getTemperature();}
    @Override public void setTemperature(double temp) {sensor.setTemperature(temp);}

    public void update(){}

}