package controller.display;

import javafx.fxml.Initializable;
import metier.sensor.ISensor;

public abstract class ControllerDisplay implements Initializable {
    /**
     * The sensor to be displayed
     */
    protected ISensor sensor;

    /**
     * Set the sensor to be displayed.
     * @param sensor the sensor to be displayed
     */
    abstract void setSensor(ISensor sensor);
}
