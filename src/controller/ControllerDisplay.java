package controller;

import metier.sensor.ISensor;

public abstract class ControllerDisplay {

    protected ISensor sensor;

    public ISensor getSensor() {
        return sensor;
    }
    abstract void setSensor(ISensor sensor);
}
