package controller;

import javafx.fxml.Initializable;
import metier.Sensor.ISensor;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDigital implements Initializable {
    ISensor sensor;

    public ISensor getSensor() {
        return sensor;
    }

    void setSensor(ISensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
