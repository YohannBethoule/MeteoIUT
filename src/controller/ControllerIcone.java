package controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import metier.sensor.ISensor;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerIcone extends ControllerDisplay implements Initializable {
    static final String SNOW = "/ressources/flocon.png" ;
    static final String SUN = "/ressources/sun.png";
    static final String CLOUD = "/ressources/cloud.png";

    @FXML ImageView imgThermo;
    @FXML Label lbName;
    @FXML Label lbIndicator;

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

    @Override
    public void setSensor(ISensor sensor) {
        this.sensor = sensor;

        setPathImg(sensor.getTemperature());
        setImage(new Image(getPathImg()));
        lbName.textProperty().bind(sensor.nameProperty());
        lbIndicator.textProperty().bind(sensor.temperatureProperty().asString());
        imgThermo.imageProperty().bind(imageProperty());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
