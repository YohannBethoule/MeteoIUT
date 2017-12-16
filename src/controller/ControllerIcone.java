package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import metier.Sensor.ISensor;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIcone implements Initializable {
    static final String SNOW = "/ressources/flocon.png" ;
    static final String SUN = "/ressources/sun.png";
    static final String CLOUD = "/ressources/cloud.png";

    ISensor sensor;

    private ObjectProperty<Image> image= new SimpleObjectProperty<Image>();
    private StringProperty pathImg = new SimpleStringProperty();

    public Image getImage() {
        return image.get();
    }
    public String getPathImg() {
        return pathImg.get();
    }
    public void setPathImg(double temp) {
        if(temp>=20) this.pathImg.set(SUN);
        if(temp>=0 && temp<20)this.pathImg.setValue(CLOUD) ;
        if(temp<0)this.pathImg.set(SNOW);
    }
    public void setImage(Image img) {
        this.image.set(img);
    }
    public StringProperty pathImgProperty(){
        return pathImg;
    }
    public ObjectProperty<Image> imageProperty() {
        return image;
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
