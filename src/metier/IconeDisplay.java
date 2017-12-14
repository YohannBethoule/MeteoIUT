package metier;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import metier.Sensor.ISensor;

public class IconeDisplay extends DisplaySensor {
    static final String SNOW = "/ressources/flocon.png" ;
    static final String SUN = "/ressources/sun.png";
    static final String CLOUD = "/ressources/cloud.png";

    private ObjectProperty<Image> image= new SimpleObjectProperty<Image>();
    private StringProperty pathImg = new SimpleStringProperty();

    public IconeDisplay(ISensor sensor) {
        super(sensor);
    }

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
    public ObjectProperty display() {
        displayedSensor.update();
        setPathImg(displayedSensor.getTemperature());
        setImage(new Image(getPathImg()));
        return imageProperty();
    }
}
