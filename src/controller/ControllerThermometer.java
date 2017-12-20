package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private DoubleProperty progressTemperature=new SimpleDoubleProperty();

    public double getProgressTemperature(){ return progressTemperature.get(); }
    public void setProgressTemperature(double temp) {
        if(sensor.getTemperature()<0){
            temp=ZERO_PROGB-abs(sensor.getTemperature()/100);
        }
        temp= (sensor.getTemperature()/100.0)+ZERO_PROGB;
        if(temp >=1){temp=1;}
        this.progressTemperature.set(temp);
    }
    public DoubleProperty progressTemperatureProperty(){
        return progressTemperature;
    }

    @Override
    public void setSensor(ISensor sensor) {
        this.sensor = sensor;
        setProgressTemperature(sensor.getTemperature());
        thermometer.progressProperty().bind(progressTemperatureProperty());
        lbName.textProperty().bind(sensor.nameProperty());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
