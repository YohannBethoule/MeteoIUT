package metier.sensor;

import javafx.application.Platform;
import javafx.beans.property.*;
import metier.generation.ITemperatureGenerator;
import metier.generation.IntervalGeneration;
import metier.generation.RelativeGeneration;

import static java.lang.Math.abs;

public class SimpleSensor extends ISensor {

    private ITemperatureGenerator tempGenerator;

    public SimpleSensor(String name,ITemperatureGenerator generator){
        super(name);
        tempGenerator = generator;
        update();
    }

    @Override
    public void update(){
        if(tempGenerator.getClass()== RelativeGeneration.class){
            int interval=((RelativeGeneration)tempGenerator).getInterval();
            tempGenerator=new RelativeGeneration(getTemperature(), interval);
        }
        double t = tempGenerator.generateTemperature();
        setTemperature(t);
    }

    public ITemperatureGenerator getTempGenerator(){
        return tempGenerator;
    }

}
