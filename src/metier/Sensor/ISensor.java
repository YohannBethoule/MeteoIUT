package metier.Sensor;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 * @author Lulauriche et Yobethoule
 * Interface
 */
public interface ISensor {
    /**
     * @return the name of the sensor
     */
    public String getName();


    /**
     *
     * @return the name of the sensor as a StringProperty
     */
    public StringProperty nameProperty();

    /**
     * Change the name of the sensor.
     * @param name the new name of the sensor
     */
    public void setName(String name);


    /**
     *
     * @return the current temperature of the sensor
     */
    public double getTemperature();

    /**
     *
     * @return the current temperature of the sensor as a DoubleProperty
     */
    public DoubleProperty temperatureProperty();
    /**
     * Change the temperature of the sensor.
     * @param temp the new value for the temperature
     */
    void setTemperature(double temp);

    /**
     * Update the temperature of the sensor, with the strategy implemented by the class.
     */
    public void update();

}
