package metier;

import javafx.beans.property.*;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import static java.lang.Math.abs;

public class SimpleSensor implements ISensor {
    private int refreshRate;
    static final double ZERO_PROGB = 0.5;
    static final String SNOW = "/ressources/flocon.png" ;
    static final String SUN = "/ressources/sun.png";
    static final String CLOUD = "/ressources/cloud.png";

    private StringProperty pathImg = new SimpleStringProperty();
    private StringProperty name=new SimpleStringProperty();
    private DoubleProperty temperature=new SimpleDoubleProperty();
    private DoubleProperty progressTemperature=new SimpleDoubleProperty();
    private ObjectProperty<Image> image= new SimpleObjectProperty<Image>();

    private ITemperatureGenerator tempGenerator;

    public SimpleSensor(String name, int refreshRate){
        this.name.set(name);
        this.refreshRate=refreshRate;
        this.tempGenerator=new IntervalGeneration();
        update();
    }

    public Image getImage() {
        return image.get();
    }
    public String getName() { return name.get(); }
    public Double getTemperature(){ return temperature.get(); }
    public String getPathImg() {
        return pathImg.get();    }
    public double getProgressTemperature(){ return progressTemperature.get(); }

    public void setTemperature(double temperature) {
        this.temperature.set(temperature);
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setProgressTemperature(double temp) {
        if(getTemperature()<0){
            temp=ZERO_PROGB-abs(getTemperature()/100);
        }
        temp= (getTemperature()/100.0)+ZERO_PROGB;
        if(temp >=1){temp=1;}
        this.progressTemperature.set(temp);
    }
    public void setPathImg(double temp) {
        if(temp>=10) this.pathImg.set(SUN);
        if(temp>=0 && getTemperature()<10)this.pathImg.setValue(CLOUD) ;
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
    public StringProperty nameProperty() {
        return name;
    }
    public DoubleProperty temperatureProperty(){ return temperature; }
    public DoubleProperty progressTemperatureProperty(){
        return progressTemperature;
    }

    @Override
    public void update(){
        double t = tempGenerator.generateTemperature();
        setTemperature(t);
        setProgressTemperature(t);
        setPathImg(t);
        setImage(new Image(getPathImg()));
    }

}
