package metier;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

public class SuperSensor implements ISensor{
    private ObservableList<ISensor> sensors;

    public SuperSensor(){

    }

    @Override
    public String getPathImg() {
        return null;
    }

    @Override
    public void setPathImg(double temp) {

    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public void setImage(Image img) {

    }

    @Override
    public StringProperty pathImgProperty() {
        return null;
    }

    @Override
    public ObjectProperty<Image> imageProperty() {
        return null;
    }

    @Override
    public void setProgressTemperature(double temp) {

    }

    @Override
    public DoubleProperty progressTemperatureProperty() {
        return null;
    }

    @Override
    public double getProgressTemperature() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public StringProperty nameProperty() {
        return null;
    }

    @Override
    public void setName(String Name) {

    }

    @Override
    public Double getTemperature() {
        return null;
    }

    @Override
    public DoubleProperty temperatureProperty() {
        return null;
    }

    @Override
    public void setTemperature(double temperature) {

    }

    @Override
    public void update() {

    }
}
