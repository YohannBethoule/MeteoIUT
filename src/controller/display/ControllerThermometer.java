package controller.display;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import metier.sensor.ISensor;

public class ControllerThermometer extends ControllerDisplay {
    static final double ZERO_PROGB = 0.5;

    @FXML ProgressBar thermometer;
    @FXML Label lbName;

    @Override
    public void setSensor(ISensor sensor) {
        this.sensor = sensor;
        thermometer.progressProperty().bind(this.sensor.temperatureProperty().add(10).divide(100).multiply(2));
        lbName.textProperty().bind(this.sensor.nameProperty());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
