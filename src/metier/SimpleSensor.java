package metier;

import javafx.beans.property.*;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import static java.lang.Math.abs;

public class SimpleSensor implements ISensor {
    private int refreshRate;
    private String pathImg;
    static final double ZERO_PROGB = 0.5;
    static final String SNOW = "/ressources/flocon.png" ;
    static final String SUN = "/ressources/sun.png";
    static final String CLOUD = "/ressources/cloud.png";

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
    public String getPathImg() { return pathImg;    }
    public double getProgressTemperature(){ return progressTemperature.get(); }

    public void setTemperature(Double temperature) {
        this.temperature.set(temperature);
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setProgressTemperature() {
        double temp;
        if(getTemperature()<0){
            temp=ZERO_PROGB-abs(getTemperature()/100);
        }
        temp= (getTemperature()/100.0)+ZERO_PROGB;
        if(temp >=1){temp=1;}
        this.progressTemperature.set(temp);
    }
    public void setPathImg() {
        if(getTemperature()>=10){
            this.pathImg = SUN ;
        }
        if(getTemperature()>=0 && getTemperature()<10){
            this.pathImg = CLOUD ;
        }
        this.pathImg= SNOW;

    }
    public void setImage() {
        Image img = new Image(getPathImg());
        this.image.set(img);
    }

    public ObjectProperty<Image> imageProperty() {
        return image;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public DoubleProperty temperatureProperty(){ return temperature; }
    public DoubleProperty progessTemperatureProperty(){
        setProgressTemperature();
        return progressTemperature;
    }

    public void update(){
        setTemperature(tempGenerator.generateTemperature());
    }

}
