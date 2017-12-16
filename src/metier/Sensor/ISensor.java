package metier.Sensor;

import javafx.beans.property.*;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;

/**
 * @author Lulauriche et Yobethoule
 * Interface
 */
public abstract class ISensor {
    protected StringProperty name=new SimpleStringProperty();
    private DoubleProperty temperature=new SimpleDoubleProperty();

    /**
     * @return the name of the sensor
     */
    public String getName(){
        return name.get();
    }


    /**
     *
     * @return the name of the sensor as a StringProperty
     */
    public StringProperty nameProperty(){
        return name;
    }


    /**
     * Change the name of the sensor.
     * @param name the new name of the sensor
     */
    public void setName(String name){
        this.name.set(name);
    }


    /**
     *
     * @return the current temperature of the sensor
     */
    public double getTemperature(){
        return this.temperature.get();
    }

    /**
     *
     * @return the current temperature of the sensor as a DoubleProperty
     */
    public DoubleProperty temperatureProperty(){
        return temperature;
    }


    /**
     * Change the temperature of the sensor.
     * @param temp the new value for the temperature
     */
    void setTemperature(double temp){
        this.temperature.set(temp);
    }

    /**
     * Update the temperature of the sensor, with the strategy implemented by the class.
     */
    public abstract void update();

}
