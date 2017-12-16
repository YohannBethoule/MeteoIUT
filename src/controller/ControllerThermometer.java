package controller;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.Initializable;
import metier.Sensor.ISensor;

import java.net.URL;
import java.util.ResourceBundle;

import static java.lang.Math.abs;

public class ControllerThermometer implements Initializable {
    private ISensor selectedSensor;
    static final double ZERO_PROGB = 0.5;

    ISensor sensor;

    private DoubleProperty progressTemperature=new SimpleDoubleProperty();

    public double getProgressTemperature(){ return progressTemperature.get(); }
    public void setProgressTemperature(double temp) {
        if(selectedSensor.getTemperature()<0){
            temp=ZERO_PROGB-abs(selectedSensor.getTemperature()/100);
        }
        temp= (selectedSensor.getTemperature()/100.0)+ZERO_PROGB;
        if(temp >=1){temp=1;}
        this.progressTemperature.set(temp);
    }
    public DoubleProperty progressTemperatureProperty(){
        return progressTemperature;
    }


    public ISensor getSensor() {
        return sensor;
    }

    public void setSensor(ISensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
