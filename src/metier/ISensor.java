package metier;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Lulauriche et Yobethoule
 * Interface
 */
public interface ISensor {
    static final int minTemp=-20;
    static final int maxTemp=40;

    /**
     * Change the temperature of the sensor for displaying in a progessBar.
     * @param ProgressTemperature the temperature to convert.
     */
    public void setProgressTemperature();

    /**
     *
     * @return the progressBar temperature as a DoubleProerty
     */
    public DoubleProperty progessTemperatureProperty();

    /**
     *
     * @return the progressBar temperature as a double
     */
    public double getProgressTemperature();
    /**
     * @return the name of the sensor
     */
    public String getName();

    /**
     *
     * @return the name of the sensor as a StringProperty
     */
    public StringProperty nameProperty() ;

    /**
     * Change the name of the sensor.
     * @param Name the new name of the sensor
     */
    public void setName(String Name);


    /**
     *
     * @return the current temperature of the sensor
     */
    public Double getTemperature();

    /**
     *
     * @return the current temperature of the sensor as a DoubleProperty
     */
    public DoubleProperty temperatureProperty();

    /**
     * Change the temperature of the sensor.
     * @param temperature the new value for the temperature
     */
    void setTemperature(Double temperature);

    /**
     * Update the temperature of the sensor, with the strategy implemented by the class.
     */
    public void update();

}
