package controller.display;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import java.net.URL;
import java.util.ResourceBundle;
import metier.sensor.ISensor;

import static java.lang.Math.abs;

public class ControllerThermometer extends ControllerDisplay {
    static final double ZERO_PROGB = 0.5;

    @FXML ProgressBar thermometer;
    @FXML Label lbName;
/*
    private DoubleProperty progressTemperature=new SimpleDoubleProperty();

    public double getProgressTemperature(){ return progressTemperature.get(); }
    public void setProgressTemperature(double temp) {
        if(temp<0){
            temp=ZERO_PROGB-abs(temp/100);
        }
        temp= (temp/100.0)+ZERO_PROGB;
        if(temp >=1){temp=1;}
        this.progressTemperature.set(temp);
    }
    public DoubleProperty progressTemperatureProperty(){
        return progressTemperature;
    }
*/
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
