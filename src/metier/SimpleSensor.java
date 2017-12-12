package metier;

import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.concurrent.Task;
import javafx.scene.image.Image;

import static java.lang.Math.abs;

public class SimpleSensor implements ISensor {
    private int refreshRate;

    private StringProperty name=new SimpleStringProperty();
    private DoubleProperty temperature=new SimpleDoubleProperty();

    private ITemperatureGenerator tempGenerator;

    public SimpleSensor(String name, int refreshRate){
        this.name.set(name);
        this.refreshRate=refreshRate;
        this.tempGenerator=new IntervalGeneration();
        update();
    }

    public String getName() { return name.get(); }
    public Double getTemperature(){ return temperature.get(); }

    public void setTemperature(double temperature) {
        Platform.runLater(()->{
            this.temperature.set(temperature);
        });
    }
    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }
    public DoubleProperty temperatureProperty(){ return temperature; }

    @Override
    public void update(){
        double t = tempGenerator.generateTemperature();
        setTemperature(t);
    }

}
