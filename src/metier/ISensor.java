package metier;

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
    static final int MIN_TEMP =-20;
    static final int MAX_TEMP =40;
    static final double ZERO_PROGB = 0.5;
    static final String SNOW = "/ressources/flocon.png" ;
    static final String SUN = "/ressources/sun.png";
    static final String CLOUD = "/ressources/cloud.png";



    /**
     * @return the path of the selected image
     */
    public String getPathImg();
    /**
     * set the path of the image
     * @param temp is the actual température to generate the image's path
     */
     public void setPathImg(double temp);
    /**
     *
     * @return the Image of the Property.
     */
    public Image getImage();
    /**
     * Create a new image with different path and set it on the Property.
     */
    public void setImage(Image img);
    /**
     *
     * @return a StringProperty which gives the path of the image
     */
    public StringProperty pathImgProperty();
    /**
    * @return the Image as a Imageproperty.
     */
    public ObjectProperty<Image> imageProperty();
    /**
     * Change the temperature of the sensor for displaying in a progessBar.
     */
    public void setProgressTemperature(double temp);
    /**
     *
     * @return the progressBar temperature as a DoubleProperty.
     */
    public DoubleProperty progressTemperatureProperty();
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
     * @param name the new name of the sensor
     */
    public void setName(String name);
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
     * @param temp the new value for the temperature
     */
    void setTemperature(double temp);

    /**
     * Update the temperature of the sensor, with the strategy implemented by the class.
     */
    public void update();

}
