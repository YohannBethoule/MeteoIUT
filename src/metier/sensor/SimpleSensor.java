package metier.sensor;

import javafx.application.Platform;
import javafx.beans.property.*;
import metier.generation.ITemperatureGenerator;
import metier.generation.IntervalGeneration;
import metier.generation.RelativeGeneration;

import static java.lang.Math.abs;

public class SimpleSensor extends ISensor {
    /**
     * The TemperatureGenerator managing the temperature generation method
     */
    private ITemperatureGenerator tempGenerator;

    public SimpleSensor(String name,ITemperatureGenerator generator){
        super(name);
        tempGenerator = generator;
        update();
    }

    /**
     * Update the temperature of the sensor by generating a new one.
     */
    @Override
    public void update(){
        if(tempGenerator.getClass()== RelativeGeneration.class){
            int interval=((RelativeGeneration)tempGenerator).getInterval();
            tempGenerator=new RelativeGeneration(getTemperature(), interval);
        }
        double t = tempGenerator.generateTemperature();
        setTemperature(t);
    }

    /**
     * Getter to the TemperatureGenerator of the sensor.
     * @return the TemperatureGenerator of the sensor
     */
    public ITemperatureGenerator getTempGenerator(){
        return tempGenerator;
    }

}
