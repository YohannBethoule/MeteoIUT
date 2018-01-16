package controller.display;

import javafx.fxml.Initializable;
import metier.sensor.ISensor;

public abstract class ControllerDisplay implements Initializable {

    protected ISensor sensor;
    abstract void setSensor(ISensor sensor);
}
