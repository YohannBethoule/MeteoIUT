package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import metier.sensor.ISensor;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerDigital implements Initializable {
    ISensor sensor;

    @FXML Label lbName;
    @FXML Label lbDigital;


    public ISensor getSensor() {
        return sensor;
    }

    void setSensor(ISensor sensor) {
        this.sensor = sensor;
        lbName.textProperty().bind(sensor.nameProperty());
        lbDigital.textProperty().bind(sensor.temperatureProperty().asString());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
